import {Divider, List, ListItemIcon, ListItemText, MenuItem, Stack, Switch} from "@mui/material";
import {DarkMode, EditRounded, Translate} from "@mui/icons-material";
import Typography from "@mui/material/Typography";
import * as React from "react";
import {useContext} from "react";
import {AppCache, LanguageContext, useLocalStorage} from "../../../base";
import PropTypes from "prop-types";
import {ValueType} from "../../../base/route/ValueType";

export function SettingsPopper(props) {

    const {
        editTitle,
        editOnClick,
    } = props

    const {i18n, isLocEn, t} = useContext(LanguageContext)
    const darkMode = useLocalStorage("darkMode", ValueType.bool);

    const [switchLocEn, setSwitchLocEn] = React.useState(!isLocEn);
    const [switchDark, setSwitchDark] = React.useState(darkMode);

    return (
        <React.Fragment>
            <Stack spacing={2}>
                <List
                    component="nav"
                    sx={{
                        width: '100%',
                        '& .MuiListItemButton-root': {
                            borderRadius: 2,
                        },
                        // hover states
                        '& .MuiListItemButton-root:hover': {
                            bgcolor: '#ebebeb78',
                        },

                    }}
                >
                    <MenuItem onClick={editOnClick} sx={{
                        paddingY: '13px'
                    }}>
                        <ListItemIcon>
                            <EditRounded/>
                        </ListItemIcon>
                        <ListItemText primary={<Typography variant="body1">{editTitle}</Typography>}/>
                    </MenuItem>

                    <Divider/>

                    <MenuItem onClick={() => {
                        i18n.changeLanguage(!switchLocEn ? 'ru' : 'en')
                        setSwitchLocEn(!switchLocEn)
                    }}>
                        <ListItemIcon>
                            <Translate/>
                        </ListItemIcon>
                        <ListItemText primary={<Typography variant="body1">
                            {t('common.menu_en_ru')}
                        </Typography>}/>
                        <Typography variant="body2" color="text.secondary">
                            <Switch checked={switchLocEn}/>
                        </Typography>
                    </MenuItem>

                    <MenuItem onClick={() => {
                        AppCache.booleanSet('darkMode', !darkMode)
                        setSwitchDark(!switchDark)
                    }}>
                        <ListItemIcon>
                            <DarkMode/>
                        </ListItemIcon>
                        <ListItemText primary={<Typography variant="body1">
                            {t('common.menu_theme')}
                        </Typography>}/>
                        <Typography variant="body2" color="text.secondary">
                            <Switch checked={switchDark}/>
                        </Typography>
                    </MenuItem>
                </List>
            </Stack>
        </React.Fragment>
    );
}

SettingsPopper.propTypes = {
    editTitle: PropTypes.string.isRequired,
    editOnClick: PropTypes.func.isRequired,
};