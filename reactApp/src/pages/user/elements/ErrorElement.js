import * as React from 'react';
import {Box, Stack, Typography} from "@mui/material";
import Lottie from "lottie-react";
import {ConstantLottie} from "../../../base";
import PropTypes from "prop-types";

export function ErrorElement(props) {

    const {error} = props

    return (
        <Box sx={{
            width: '100%',
            height: '100%'
        }}>
            <Stack
                justifyContent="center"
                alignItems="center"
                spacing={0}
                sx={{
                    width: '100%',
                    height: '100%',
                }}
            >
                <Lottie style={{
                    width: 300,
                }} animationData={ConstantLottie.error}/>

                <Typography variant="h5" sx={{
                    paddingBottom: '90px'
                }}>
                    {error.message}
                </Typography>
            </Stack>
        </Box>

    )
}

ErrorElement.propTypes = {
    error: PropTypes.object.isRequired
};