import * as React from 'react';
import {useContext} from 'react';
import {Box, Button, Stack, Typography} from "@mui/material";
import Lottie from "lottie-react";
import {ConstantKMM, ConstantLottie, LanguageContext, NavigateContext, useLocalStorage} from "../../base";
import {ValueType} from "../../base/route/ValueType";

export function WelcomePage() {

    const darkMode = useLocalStorage("darkMode", ValueType.bool);
    const greeting = ConstantKMM.greeting

    const {route, routes} = useContext(NavigateContext)
    const {t} = useContext(LanguageContext)

    return (
        <Stack
            className={'Welcome-Page'}
            spacing={3}
            sx={{
                p: 5,
                height: '100%',
                boxSizing: 'border-box',
                backgroundColor: 'background.default'
            }}
            direction="column"
            justifyContent="space-between"
            alignItems="center"
        >
            <Box className={'BgCircle Circle1'} sx={{background: darkMode ? '#8963e8' : '#ffe1eb'}}/>
            <Box className={'BgCircle Circle2'} sx={{background: darkMode ? '#d2c0ff' : '#d8c7ff'}}/>
            <Box className={'BgCircle Circle3'} sx={{background: darkMode ? '#6f46d1' : '#7b4fdb'}}/>
            <Box className={'BgCircle Circle4'} sx={{background: darkMode ? '#e88fad' : '#ec99b7'}}/>

            <Stack
                spacing={3}
                alignItems="center"
                position={'relative'}
            >
                <Typography variant="h1" color='text.primary'>
                    {t('welcome.title')}
                </Typography>

                <Typography variant="h6" color='text.primary'>
                    {t('welcome.subtitle')}
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
                        route.toLocation(routes.signIn)
                    }}
                >
                    {t('welcome.btn')}
                </Button>

                <Typography variant="body2" align={"right"} color='text.secondary'>
                    {t('welcome.version')} {greeting}
                </Typography>
            </Stack>

        </Stack>
    );
}

WelcomePage.propTypes = {};