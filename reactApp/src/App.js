import * as React from 'react';
import {useContext} from 'react';
import {ThemeProvider} from "@mui/material";
import {AppTheme} from "./theme/AppTheme";
import {AppThemeDark} from "./theme/AppThemeDark";
import {NavigateContext, useLocalStorage} from "./base";
import {ValueType} from "./base/route/ValueType";

export default function App() {

    const {route} = useContext(NavigateContext)
    const darkMode = useLocalStorage("darkMode", ValueType.bool);

    document.body.style = `background: ${darkMode ? AppThemeDark.palette.background.default : AppTheme.palette.background.default};`;

    return (
        <ThemeProvider theme={darkMode ? AppThemeDark : AppTheme}>
            {route.render()}
        </ThemeProvider>
    );
}
