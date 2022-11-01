import React, {createContext, useState} from "react";
import {useLocation, useNavigate, useNavigationType} from "react-router-dom";
import RouteCore from "../route/RouteCore";
import {RouteConf} from "../route/RouteConf";
import {RouteUserConf} from "../route/RouteUserConf";

export const NavigateContext = createContext({})

export default function NavigateContextProvider(props) {

    let conf = RouteConf

    const code = localStorage.getItem("code")

    if (code != null && code !== "") {
        conf = RouteUserConf
    }

    else if (window.location.search.includes("code")) {
        window.location.search.replace("?", "").split("&").forEach((value) => {
            const [key, val] = value.split("=")
            if (key === "code" && val !== "") {
                localStorage.setItem(key, val)
                conf = RouteUserConf
            }
        })
    }

    const location = useLocation()
    const navigate = useNavigate()
    const type = useNavigationType()

    const [route] = useState(new RouteCore(conf, location, navigate, type));

    route.update(location, navigate, type)

    return (
        <NavigateContext.Provider
            value={{
                route,
                routes: conf.routes
            }}>
            {props.children}
        </NavigateContext.Provider>
    )
}