import * as React from 'react';
import {Box, Stack} from "@mui/material";
import Lottie from "lottie-react";
import {ConstantLottie} from "../../../base";

export function LoadingElement() {

    return (
        <Box sx={{
            width: '100%',
            height: '100%'
        }}>
            <Stack
                direction="row"
                justifyContent="center"
                alignItems="center"
                spacing={2}
                sx={{
                    width: '100%',
                    height: '100%'
                }}
            >
                <Lottie style={{
                    width: 300,
                }} animationData={ConstantLottie.server}/>
            </Stack>
        </Box>

    )
}

LoadingElement.propTypes = {};