import * as React from 'react';
import {useContext} from 'react';
import {Box, Button, MobileStepper, Stack, Typography} from "@mui/material";
import {KeyboardArrowLeft, KeyboardArrowRight} from "@mui/icons-material";
import {ConstantLottie, NavigateContext} from "../../base";
import Lottie from "lottie-react";

const steps = [
    {
        label: 'About the app',
        icon: ConstantLottie.step1,
        iconWidth: 200,
        description: `This is a demo application using the open GitHub REST API.
        The application is under development. Develop will take place in 3 stages.`,
    },
    {
        label: 'First stage',
        icon: ConstantLottie.step2,
        iconWidth: 175,
        description:
            'Jetpack Compose application, MVVM and the whole latest stack surrounding this technology',
    },
    {
        label: 'Second stage',
        icon: ConstantLottie.step3,
        iconWidth: 175,
        description: `Development of a shared KMM module for interacting with an ios application`,
    },
    {
        label: 'Third stage',
        icon: ConstantLottie.step4,
        iconWidth: 270,
        description: `Application for ios on Swift using the previously developed shared module using KMM`,
    },
    {
        label: 'Acknowledgments',
        icon: ConstantLottie.step5,
        iconWidth: 200,
        description: `This app uses the latest Google & JetBrains & Apple.
        Thank them for this opportunity!
        Thank you for your interest!
        Thanks to my wife for your patience :)`,
    },
];

export function OnboardingPage() {

    const {route, routes} = useContext(NavigateContext)

    const [activeStep, setActiveStep] = React.useState(0);
    const maxSteps = steps.length;

    const handleDone = () => {
        route.toLocation(routes.welcome)
    };

    const handleNext = () => {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    return (
        <Box sx={{
            width: '100%',
            height: '100%',
            flexGrow: 1,
            p: 3,
            boxSizing: 'border-box'
        }}>

            <Box sx={{
                position: 'relative',
                height: 'calc(100% - 48px)'
            }}>
                <Stack align={'center'} spacing={3} sx={{
                    position: 'absolute',
                    top: '50%',
                    left: 0,
                    right: 0,
                    transform: 'translateY(-50%)'
                }}>
                    <Lottie style={{
                        width: steps[activeStep].iconWidth,
                        margin: '0 auto'
                    }} animationData={steps[activeStep].icon}/>

                    <Typography variant="h6" style={{}}>
                        {steps[activeStep].label}
                    </Typography>

                    <Typography variant="body1" style={{
                        paddingTop: '20px',
                        maxWidth: 400,
                        margin: '0 auto'
                    }}>
                        {steps[activeStep].description}
                    </Typography>

                </Stack>
            </Box>

            <MobileStepper
                variant="dots"
                steps={maxSteps}
                position="static"
                activeStep={activeStep}
                sx={{
                    width: '100%',
                    flexGrow: 1,
                    boxSizing: 'border-box'
                }}
                nextButton={
                    (activeStep === maxSteps - 1 ? (
                        <Button size="small" onClick={handleDone} sx={{
                            padding: '5px 13px'
                        }}>
                            Finish
                        </Button>
                    ) : (
                        <Button size="small" onClick={handleNext}>
                            Next
                            <KeyboardArrowRight/>
                        </Button>
                    ))
                }
                backButton={
                    <Button size="small" onClick={handleBack} disabled={activeStep === 0}>
                        <KeyboardArrowLeft/>
                        Back
                    </Button>
                }
            />
        </Box>
    );
}

OnboardingPage.propTypes = {};