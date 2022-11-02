import * as React from 'react';
import {CircularProgress, Stack} from "@mui/material";
import {ConstantImages, ConstantKMM, NavigateContext} from "../../base";
import {useContext, useEffect} from "react";
import {SnackbarError} from "../../components";

function getCode(): string {
    let code = null
    if (window.location.search.includes("code")) {
        window.location.search.replace("?", "").split("&").forEach((value) => {
            const [key, val] = value.split("=")
            if (key === "code") {
                code = val
            }
        })
    }
    return code
}

export function SplashPage() {

    const {route, routes} = useContext(NavigateContext)

    const [errorAuth, setErrorAuth] = React.useState(null);
    const [authCode, setAuthCode] = React.useState(null);
    const [loading, setLoading] = React.useState(false);

    useEffect(() => {
        const code = getCode()
        if (ConstantKMM.crossStorage.authToken) {
            route.toLocation(routes.user)
        } else if (code) {
            setAuthCode(code)
        } else if (ConstantKMM.crossStorage.isOnboardingDone) {
            route.toLocation(routes.welcome)
        } else {
            route.toLocation(routes.onboarding)
        }
    }, [route, routes.onboarding, routes.user, routes.welcome])

    useEffect(() => {
        if (authCode !== null) {
            setLoading(true)
            ConstantKMM.httpClient.post.oauth(authCode).then(async (response) => {
                ConstantKMM.crossStorage.authToken = response.accessToken
                window.location = '/'
                setLoading(false)
            }).catch(async (response) => {
                setErrorAuth(response)
                setLoading(false)
            });
        }
    }, [authCode])

    return (
        <Stack
            justifyContent="center"
            alignItems="center"
            sx={{
                width: '100%',
                height: '100%',
                background: '#9F7AFF'
            }}
        >
            <img src={ConstantImages.layout.logo192} alt={'Logo'}/>

            {loading ? (
                <CircularProgress size={'24px'} color={'secondary'} sx={{
                    position: 'absolute',
                    top: 20,
                    right: 20
                }} />
            ) : null }

            <SnackbarError error={errorAuth}/>

        </Stack>
    );
}

SplashPage.propTypes = {};