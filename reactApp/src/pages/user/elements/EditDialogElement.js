import * as React from 'react';
import {AppBar, Box, Dialog, IconButton, Toolbar, Typography} from "@mui/material";
import PropTypes from "prop-types";
import Slide from '@mui/material/Slide';
import {TransitionProps} from '@mui/material/transitions';
import {Close} from "@mui/icons-material";

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

    return (
        <Dialog
            fullScreen
            open={open}
            onClose={onClose}
            TransitionComponent={Transition}
        >
            <AppBar sx={{position: 'relative'}}>
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
        </Dialog>
    )
}

EditDialogElement.propTypes = {
    title: PropTypes.string.isRequired,
    open: PropTypes.bool.isRequired,
    onClose: PropTypes.func.isRequired,
    children: PropTypes.element.isRequired
};