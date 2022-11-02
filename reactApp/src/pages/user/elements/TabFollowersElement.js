import * as React from 'react';
import {Box, Stack} from "@mui/material";
import {useEffect, useState} from "react";
import {ConstantKMM} from "../../../base";
import {SnackbarError} from "../../../components";
import {TabBarElement} from "./TabBarElement";

export function TabFollowersElement() {

    const [count, setCount] = useState(0)
    const [error, setError] = React.useState(null);
    const [loading, setLoading] = React.useState(false);

    useEffect(() => {
        setLoading(true)
        ConstantKMM.httpClient.get.followers(1).then(async (response) => {
            setCount(response.toArray().length)
            setLoading(false)
        }).catch(async (response) => {
            setError(response)
            setLoading(false)
        });
    }, [])

    return (
        <>
            <SnackbarError error={error}/>
            <Stack>
                <TabBarElement title={"Follower"}/>
                <Box sx={{p: 3}}>
                    TabFollowersElement. Followers response count: {loading ? "loading..." : count}
                </Box>
            </Stack>
        </>
    )
}

TabFollowersElement.propTypes = {};