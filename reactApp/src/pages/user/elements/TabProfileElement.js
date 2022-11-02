import * as React from 'react';
import {Box, Stack} from "@mui/material";
import {useEffect, useState} from "react";
import {ConstantKMM} from "../../../base";
import {SnackbarError} from "../../../components";
import {TabBarElement} from "./TabBarElement";

export function TabProfileElement() {

    const [user, setUser] = useState(null)
    const [error, setError] = React.useState(null);
    const [loading, setLoading] = React.useState(false);

    useEffect(() => {
        setLoading(true)
        ConstantKMM.httpClient.get.user().then(async (response) => {
            setUser(response)
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
                <TabBarElement title={"Profile"} onClick={loading ? null : () => {
                    console.log("ye")
                }}/>
                <Box sx={{p: 3}}>
                    TabProfileElement. User name: {loading ? "loading..." : (user?.name ?? "loading...")}
                </Box>
            </Stack>
        </>
    )
}

TabProfileElement.propTypes = {};