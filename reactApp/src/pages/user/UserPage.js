import * as React from 'react';
import {useEffect} from 'react';
import {
    Box,
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle,
    Stack,
    Tab,
    Tabs,
    Typography,
    useTheme
} from "@mui/material";
import {FormatListBulleted, Logout, People, Person} from "@mui/icons-material";
import {TabReposElement} from "./elements/TabReposElement";
import {TabFollowersElement} from "./elements/TabFollowersElement";
import {TabProfileElement} from "./elements/TabProfileElement";
import {ConstantImages} from "../../base";

interface TabPanelProps {
    children?: React.ReactNode;
    index: number;
    value: number;
}

function TabPanel(props: TabPanelProps) {
    const {children, value, index, ...other} = props;

    return (
        <div
            style={{width: '100%'}}
            role="tabpanel"
            hidden={value !== index}
            id={`vertical-tabpanel-${index}`}
            aria-labelledby={`vertical-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box sx={{p: 3}}>
                    {children}
                </Box>
            )}
        </div>
    );
}

function a11yProps(index: number) {
    return {
        id: `vertical-tab-${index}`,
        'aria-controls': `vertical-tabpanel-${index}`,
    };
}

export function UserPage() {

    const theme = useTheme()

    useEffect(() => {
        document.body.style = `background: ${theme.palette.background};`;
    });

    const [value, setValue] = React.useState(0);
    const [openLogout, setOpenLogout] = React.useState(false);

    const handleClickOpen = () => {
        setOpenLogout(true);
    };

    const handleClose = () => {
        setOpenLogout(false);
    };

    return (
        <React.Fragment>
            <Box
                sx={{flexGrow: 1, bgcolor: 'background.paper', display: 'flex', height: '100%'}}
            >
                <Stack sx={{
                    backgroundColor: 'secondary.light',
                    position: 'relative'
                }}>
                    <Box align={"center"} sx={{
                        paddingTop: '20px',
                        paddingBottom: 2,
                        backgroundColor: 'secondary.main',
                    }}>
                        <img style={{
                            maxWidth: 50
                        }} src={ConstantImages.layout.logo} alt={'Logo'}/>
                    </Box>

                    <Tabs
                        orientation="vertical"
                        value={value}
                        onChange={(event: React.SyntheticEvent, newValue: number) => {
                            setValue(newValue);
                        }}
                        aria-label="Tabs menu"
                        sx={{
                            width: 110,
                            borderRight: 1,
                            borderColor: 'divider',
                            '& .Mui-selected': {
                                color: 'hwb(0deg 0% 100% / 87%)',
                            },
                        }}
                    >
                        <Tab icon={<FormatListBulleted sx={{
                            backgroundColor: 'secondary.main',
                            padding: '4px 18px',
                            borderRadius: 5,
                            marginTop: 1,
                        }}/>} label="REPOS" {...a11yProps(0)} />
                        <Tab icon={<People sx={{
                            backgroundColor: 'secondary.main',
                            padding: '4px 18px',
                            borderRadius: 5,
                            marginTop: 1,
                        }}/>} label="FOLLOWER" {...a11yProps(1)} />
                        <Tab icon={<Person sx={{
                            backgroundColor: 'secondary.main',
                            padding: '4px 18px',
                            borderRadius: 5,
                            marginTop: 1,
                        }}/>} label="PROFILE" {...a11yProps(2)} />
                    </Tabs>

                    <Button variant="contained" color="secondary" style={{boxShadow: "none"}} sx={{
                        borderRadius: 0,
                        paddingTop: '22px',
                        paddingBottom: '22px',
                        position: 'absolute',
                        bottom: 0,
                        left: 0,
                        right: 0
                    }} onClick={handleClickOpen}>
                        <Logout/>
                    </Button>
                </Stack>

                <TabPanel value={value} index={0}>
                    <TabReposElement/>
                </TabPanel>
                <TabPanel value={value} index={1}>
                    <TabFollowersElement/>
                </TabPanel>
                <TabPanel value={value} index={2}>
                    <TabProfileElement/>
                </TabPanel>
            </Box>

            <Dialog
                open={openLogout}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">
                    Exit the application
                </DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                        You will be redirected to the login page. Is this what you want?
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Dismiss</Button>
                    <Button onClick={handleClose} autoFocus>
                        Confirm
                    </Button>
                </DialogActions>
            </Dialog>
        </React.Fragment>
    );
}

UserPage.propTypes = {};