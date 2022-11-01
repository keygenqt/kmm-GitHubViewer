import * as React from 'react';
import {Box, Button, Stack, Typography} from "@mui/material";
import shared from "shared";
import Lottie from "lottie-react";
import {ConstantLottie, NavigateContext} from "../../base";
import {useContext} from "react";

export function WelcomePage() {

    const greeting = new shared.com.keygenqt.viewer.Greeting().greeting()

    const {route, routes} = useContext(NavigateContext)

    return (
        <Stack
            className={'Welcome-Page'}
            spacing={3}
            sx={{
                p: 5,
                height: '100%',
                boxSizing: 'border-box'
            }}
            direction="column"
            justifyContent="space-between"
            alignItems="center"
        >
            <Box className={'BgCircle Circle1'}/>
            <Box className={'BgCircle Circle2'}/>
            <Box className={'BgCircle Circle3'}/>
            <Box className={'BgCircle Circle4'}/>

            <Stack
                spacing={3}
                alignItems="center"
                position={'relative'}
            >
                <Typography variant="h1">
                    Welcome to GitHub Viewer!
                </Typography>

                <Typography variant="h6">
                    The app is written using the latest stack on ios and android. Keywords: KMM, MVVM, Jetpack Compose,
                    SwiftUI
                </Typography>
            </Stack>

            <Lottie style={{
                position: 'relative',
                paddingTop: '10px',
                width: 350,
            }} animationData={ConstantLottie.welcome}/>

            <Stack
                spacing={3}
                alignItems="center"
                position={'relative'}
            >
                <Button
                    fullWidth
                    size={"large"}
                    variant="contained"
                    onClick={() => {
                        // route.toLocation(routes.signIn)
                        route.toLocation(routes.user)
                    }}
                >
                    Get started
                </Button>

                <Typography variant="body2" align={"right"}>
                    Version: 0.0.1 {greeting}
                </Typography>
            </Stack>

        </Stack>
    );
}

WelcomePage.propTypes = {};