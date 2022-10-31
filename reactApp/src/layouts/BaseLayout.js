import * as React from 'react';
import {useContext} from 'react';
import PropTypes from "prop-types";
import {useTheme} from "@mui/material";
import {LanguageContext} from "../base";

export function BaseLayout(props) {

    const theme = useTheme()
    const {isLocEn} = useContext(LanguageContext)

    return (
        <div className={`AppTable ${props.pageClassName} ${isLocEn ? 'EN-en' : 'RU-ru'}`}>
            <div className={"AppTableRow"}>
                <header className={"AppTableCell"} style={{
                    backgroundColor: '#4d5d83'
                }}>
                    HEADER
                </header>
            </div>
            <div className={"AppTableRow"}>
                <main className={"AppTableCell"} style={{
                    padding: props.disablePadding ? 0 : '60px 0',
                    background: props.background ? props.background : 'inherit',
                }}>
                    {props.children}
                </main>
            </div>
            <div className={"AppTableRow"}>
                <footer className={"AppTableCell"} style={{
                    backgroundColor: theme.palette.primary.dark
                }}>
                    FOOTER
                </footer>
            </div>
        </div>
    )
}

BaseLayout.propTypes = {
    background: PropTypes.string,
    disablePadding: PropTypes.bool,
    isCenter: PropTypes.bool,
    children: PropTypes.element.isRequired,
};