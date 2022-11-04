import * as React from 'react';
import {useContext} from 'react';
import {Box, Button, MobileStepper, Stack, Typography, useTheme} from "@mui/material";
import {KeyboardArrowLeft, KeyboardArrowRight} from "@mui/icons-material";
import {ConstantKMM, ConstantLottie, LanguageContext, NavigateContext} from "../../base";
import Lottie from "lottie-react";

const steps = [
    {
        icon: ConstantLottie.step1,
        iconWidth: 190,
        label: 'onboarding.item_1_title',
        description: 'onboarding.item_1_desc',
    },
    {
        icon: ConstantLottie.step2,
        iconWidth: 180,
        label: 'onboarding.item_2_title',
        description: 'onboarding.item_2_desc',
    },
    {
        icon: ConstantLottie.step3,
        iconWidth: 280,
        label: 'onboarding.item_3_title',
        description: 'onboarding.item_3_desc',
    },
    {
        icon: ConstantLottie.step4,
        iconWidth: 184,
        label: 'onboarding.item_4_title',
        description: 'onboarding.item_4_desc',
    },
    {
        icon: ConstantLottie.step5,
        iconWidth: 180,
        label: 'onboarding.item_5_title',
        description: 'onboarding.item_5_desc',
    },
];

export function OnboardingPage() {

    const theme = useTheme()
    const {t} = useContext(LanguageContext)
    const {route, routes} = useContext(NavigateContext)

    const [activeStep, setActiveStep] = React.useState(0);
    const maxSteps = steps.length;

    const handleDone = () => {
        ConstantKMM.crossStorage.isOnboardingDone = true
        route.toLocation(routes.welcome)
    };

    const handleNext = () => {
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    return (
        <Stack
            className={'Welcome-Page'}
            spacing={3}
            sx={{
                p: 5,
                height: '100%',
                boxSizing: 'border-box',
                backgroundColor: theme.palette.background.default
            }}
            direction="column"
            justifyContent="space-between"
            alignItems="center"
        >
            <Box/>

            <Stack align={'center'} spacing={3}>
                <Lottie style={{
                    width: steps[activeStep].iconWidth,
                    margin: '0 auto'
                }} animationData={steps[activeStep].icon}/>

                <Typography variant="h6" color='text.primary'>
                    {t(steps[activeStep].label)}
                </Typography>

                <Typography variant="body1" style={{
                    paddingTop: '20px',
                    maxWidth: 450,
                    margin: '0 auto'
                }} color='text.primary'>
                    {t(steps[activeStep].description)}
                </Typography>
            </Stack>

            <Box/>

            <MobileStepper
                variant="dots"
                steps={maxSteps}
                position="static"
                activeStep={activeStep}
                sx={{
                    width: '100%',
                    flexGrow: 'inherit',
                    boxSizing: 'border-box'
                }}
                nextButton={
                    (activeStep === maxSteps - 1 ? (
                        <Button size="small" onClick={handleDone}>
                            {t('onboarding.btn_done')}
                            <KeyboardArrowRight/>
                        </Button>
                    ) : (
                        <Button size="small" onClick={handleNext}>
                            {t('onboarding.btn_next')}
                            <KeyboardArrowRight/>
                        </Button>
                    ))
                }
                backButton={
                    <Button size="small" onClick={handleBack} disabled={activeStep === 0}>
                        <KeyboardArrowLeft/>
                        {t('onboarding.btn_back')}
                    </Button>
                }
            />
        </Stack>
    );
}

OnboardingPage.propTypes = {};