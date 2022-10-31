import {createTheme} from '@mui/material/styles';
import {paletteDark} from "./impl/paletteDark";
import {typography} from "./impl/typography.js";

export const AppThemeDark = createTheme({
    breakpoints: {
        values: {
            min: 350,
            xs: 420,
            xs500: 500,
            sm: 600,
            md: 900,
            lg: 1200,
            xl: 1536,
        },
    },
    palette: paletteDark(),
    typography: typography,
});