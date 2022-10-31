import * as React from 'react';
import {useContext} from 'react';
import {Box, ThemeProvider} from "@mui/material";
import {AppTheme} from "./theme/AppTheme";
import {AppThemeDark} from "./theme/AppThemeDark";
import {NavigateContext, useLocalStorage} from "./base";
import {ValueType} from "./base/route/ValueType";

export default function App() {

    const {route} = useContext(NavigateContext)
    const darkMode = useLocalStorage("darkMode", ValueType.bool);

    return (
        <ThemeProvider theme={darkMode ? AppThemeDark : AppTheme}>
            <Box id={"pageSelection"} className={"ScrollSection"} sx={{
                backgroundColor: 'background.default'
            }}>
                {route.render()}
            </Box>
        </ThemeProvider>
    );
}
