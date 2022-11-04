import * as React from 'react';
import {useEffect} from 'react';
import {
    AppBar,
    Box,
    CircularProgress,
    ClickAwayListener,
    Fade,
    IconButton,
    Paper,
    Popper,
    Stack,
    Toolbar,
    Typography,
    useTheme
} from "@mui/material";
import {Cached, Settings, Sort} from "@mui/icons-material";
import PropTypes from "prop-types";
import {useWindowResize} from "../../../base";
import {SettingsPopper} from "./SettingsPopper";

export function TabBarElement(props) {

    const {
        title = "",
        loading = false,
        isOrderASC = false,
        onRefreshClick = function () {
        },
        onSettingsClick,
        onSortClick = null,
        editTitle,
        editOnClick,
    } = props

    const theme = useTheme()

    const [rotateRefresh, setRotateRefresh] = React.useState(false);

    const [open, setOpen] = React.useState(false);
    const [anchorEl, setAnchorEl] = React.useState(null);

    const canBeOpen = open && Boolean(anchorEl);
    const id = canBeOpen ? 'transition-popper' : undefined;

    const sizeWindow = useWindowResize()

    useEffect(() => {
        setOpen(false)
    }, [sizeWindow])

    return (
        <>
            <AppBar position="static" elevation={0} sx={{
                backgroundColor: theme.palette.secondary.main,
                color: 'text.primary'
            }}>
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
                                            onClick={(event) => {
                                                setAnchorEl(event.currentTarget);
                                                event.stopPropagation();
                                                setOpen((previousOpen) => !previousOpen);
                                            }}
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

            <ClickAwayListener onClickAway={() => {
                setOpen(false);
            }}>
                <Popper
                    id={id}
                    open={open}
                    anchorEl={anchorEl}
                    transition
                    disablePortal
                    placement="bottom-end"
                    role={undefined}
                    sx={{
                        zIndex: 999
                    }}
                    popperOptions={{
                        modifiers: [
                            {
                                name: 'offset',
                                options: {
                                    offset: [-33, -10]
                                }
                            }
                        ]
                    }}
                >
                    {({TransitionProps}) => (
                        <Fade {...TransitionProps} timeout={350}>
                            <Paper style={{
                                boxShadow: 'rgb(0 0 0 / 5%) 0px 3px 5px -1px, rgb(0 0 0 / 5%) 0px 5px 8px 0px, rgb(0 0 0 / 5%) 0px 1px 14px 0px',
                            }}>
                                <SettingsPopper
                                    editTitle={editTitle}
                                    editOnClick={() => {
                                        setOpen(false)
                                        editOnClick()
                                    }}
                                />
                            </Paper>
                        </Fade>
                    )}
                </Popper>
            </ClickAwayListener>
        </>
    )
}

TabBarElement.propTypes = {
    title: PropTypes.string,
    loading: PropTypes.bool,
    isOrderASC: PropTypes.bool,
    onRefreshClick: PropTypes.func,
    onSettingsClick: PropTypes.func,
    onSortClick: PropTypes.func,
    editTitle: PropTypes.string.isRequired,
    editOnClick: PropTypes.func.isRequired,
};