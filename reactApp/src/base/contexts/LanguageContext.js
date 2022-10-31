import React, {createContext} from "react";
import {useTranslation} from "react-i18next";

export const LanguageContext = createContext({})

export default function LanguageContextProvider(props) {

    const {t, i18n} = useTranslation()

    const isLocEn = i18n.language === 'en'

    return (
        <LanguageContext.Provider
            value={{
                t,
                i18n,
                isLocEn,
            }}>
            {props.children}
        </LanguageContext.Provider>
    )
}