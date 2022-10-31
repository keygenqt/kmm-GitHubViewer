import * as React from 'react';
import {ThemeProvider} from "@mui/material";
import {AppTheme} from "./theme/AppTheme";
import {AppThemeDark} from "./theme/AppThemeDark";
import {useLocalStorage} from "./base";
import {ValueType} from "./base/route/ValueType";
import {BaseLayout} from "./layouts/BaseLayout";

export default function App() {
    const darkMode = useLocalStorage("darkMode", ValueType.bool);
    return (
        <ThemeProvider theme={darkMode ? AppThemeDark : AppTheme}>
            <BaseLayout/>
        </ThemeProvider>
    );
}
