import * as React from 'react';
import {useContext} from 'react';
import {Box, Button, Container, Stack, Typography} from "@mui/material";
import {LanguageContext, NavigateContext} from "../../base";
import Lottie from "lottie-react";
import {ConstantLottie} from "../../base/constants/ConstantLottie";

export function ErrorPage(props) {

    const {t} = useContext(LanguageContext)
    const {route} = useContext(NavigateContext)

    return (
        <Box className={'AppTable'} sx={{
            backgroundColor: '#ECF0F1',
        }}>
            <Box className={'AppTableRow'}>
                <Box className={'AppTableCell'} sx={{
                    verticalAlign: 'middle'
                }}>
                    <Container maxWidth={"sm"} className={"Page UtilsList"} sx={{
                        padding: '60px 0'
                    }}>
                        <Stack alignItems={"center"} spacing={2}>

                            <Lottie style={{
                                width: 250,
                            }} animationData={ConstantLottie.error404}/>

                            <Typography variant="h3" style={{
                                marginTop: 30
                            }}>
                                {t("pages.error.t_text")}
                            </Typography>
                            <Typography align={"center"} variant="body1" style={{
                                marginBottom: 30
                            }}>
                                {t("pages.error.t_subtext")}
                            </Typography>
                            <Button
                                onClick={() => {
                                    route.toLocation('/')
                                }}
                                variant="outlined"
                            >
                                {t("pages.error.t_btn")}
                            </Button>
                        </Stack>
                    </Container>
                </Box>
            </Box>
        </Box>
    );
}