import * as React from 'react';
import {AppBar, Box, CircularProgress, IconButton, Stack, Toolbar, Typography} from "@mui/material";
import {Cached, Settings, Sort} from "@mui/icons-material";
import PropTypes from "prop-types";

export function TabBarElement(props) {

    const {
        title = "",
        loading = false,
        isOrderASC = false,
        onRefreshClick = function () {
        },
        onSettingsClick,
        onSortClick = null
    } = props

    const [rotateRefresh, setRotateRefresh] = React.useState(false);

    return (
        <AppBar position="static" elevation={0} color={"secondary"}>
            <Toolbar>
                <Stack
                    direction="row"
                    justifyContent="space-between"
                    alignItems="center"
                    spacing={1}
                    sx={{
                        width: '100%'
                    }}
                >
                    <Stack
                        direction="row"
                        justifyContent="center"
                        alignItems="center"
                        spacing={1}
                    >
                        <Typography variant="h6">
                            {title}
                        </Typography>
                    </Stack>


                    <Stack
                        direction="row"
                        justifyContent="space-between"
                        alignItems="center"
                        spacing={1}
                    >
                        {loading ? (
                            <Box sx={{
                                p: 1,
                                paddingTop: '12px'
                            }}>
                                <CircularProgress size={'24px'}/>
                            </Box>
                        ) : (
                            <>
                                {onSortClick ? (
                                    <IconButton
                                        size="large"
                                        edge="start"
                                        color="inherit"
                                        aria-label="menu"
                                        onClick={onSortClick}
                                    >
                                        <Sort sx={!isOrderASC ? {
                                            transform: "rotate(0deg)",
                                            transitionTimingFunction: "ease-in-out",
                                            transitionProperty: 'transform',
                                            transitionDuration: '300ms'
                                        } : {
                                            transform: "rotate(180deg)",
                                            transitionTimingFunction: "ease-in-out",
                                            transitionProperty: 'transform',
                                            transitionDuration: '300ms'
                                        }}/>
                                    </IconButton>
                                ) : null}

                                <IconButton
                                    size="large"
                                    edge="start"
                                    color="inherit"
                                    aria-label="menu"
                                    onClick={() => {
                                        setRotateRefresh(!rotateRefresh)
                                        onRefreshClick()
                                    }}
                                >
                                    <Cached sx={!rotateRefresh ? {
                                        transform: "rotate(0deg)",
                                        transitionTimingFunction: "ease-in-out",
                                        transitionProperty: 'transform',
                                        transitionDuration: '300ms'
                                    } : {
                                        transform: "rotate(180deg)",
                                        transitionTimingFunction: "ease-in-out",
                                        transitionProperty: 'transform',
                                        transitionDuration: '300ms'
                                    }}/>
                                </IconButton>

                                {onSettingsClick ? (
                                    <IconButton
                                        size="large"
                                        edge="start"
                                        color="inherit"
                                        aria-label="menu"
                                        onClick={onSettingsClick}
                                    >
                                        <Settings/>
                                    </IconButton>
                                ) : null}
                            </>
                        )}

                    </Stack>

                </Stack>
            </Toolbar>
        </AppBar>
    )
}

TabBarElement.propTypes = {
    title: PropTypes.string,
    loading: PropTypes.bool,
    isOrderASC: PropTypes.bool,
    onRefreshClick: PropTypes.func,
    onSettingsClick: PropTypes.func,
    onSortClick: PropTypes.func,
};