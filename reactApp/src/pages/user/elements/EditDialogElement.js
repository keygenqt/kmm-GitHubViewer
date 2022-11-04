import * as React from 'react';
import {AppBar, Box, Dialog, IconButton, Toolbar, Typography, useTheme} from "@mui/material";
import PropTypes from "prop-types";
import Slide from '@mui/material/Slide';
import {TransitionProps} from '@mui/material/transitions';
import {Close} from "@mui/icons-material";
import {useLocalStorage} from "../../../base";
import {ValueType} from "../../../base/route/ValueType";

const Transition = React.forwardRef(function Transition(
    props: TransitionProps & {
        children: React.ReactElement;
    },
    ref: React.Ref<unknown>,
) {
    return <Slide direction="up" ref={ref} {...props} />;
});

export function EditDialogElement(props) {

    const {
        title,
        open,
        onClose,
        children
    } = props

    const theme = useTheme()
    const darkMode = useLocalStorage("darkMode", ValueType.bool);

    return (
        <Dialog
            fullScreen
            open={open}
            onClose={onClose}
            TransitionComponent={Transition}
        >
            <Box
                className={darkMode ? 'sectionDark' : 'section'}
                sx={{
                    height: '100%',
                    backgroundColor: theme.palette.background.default,
                }}
            >
                <AppBar elevation={0} sx={{
                    position: 'relative',
                    backgroundColor: theme.palette.secondary.main,
                    color: 'text.primary'
                }}>
                    <Toolbar>
                        <IconButton
                            edge="start"
                            color="inherit"
                            onClick={onClose}
                            aria-label="close"
                        >
                            <Close/>
                        </IconButton>
                        <Typography sx={{ml: 2, flex: 1}} variant="h6" component="div">
                            {title}
                        </Typography>
                    </Toolbar>
                </AppBar>
                <Box>
                    {children}
                </Box>
            </Box>
        </Dialog>
    )
}

EditDialogElement.propTypes = {
    title: PropTypes.string.isRequired,
    open: PropTypes.bool.isRequired,
    onClose: PropTypes.func.isRequired,
    children: PropTypes.element.isRequired
};