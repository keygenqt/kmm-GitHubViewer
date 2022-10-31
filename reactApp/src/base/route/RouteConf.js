import {Route} from "react-router-dom";
import {BaseLayout} from "../../layouts/BaseLayout";
import * as React from "react";

export const RouteConf = {
    delay: 200,
    routes: {
        home: {
            path: '/',
            render: function (key, path) {
                return <Route
                    key={key}
                    exact
                    path={path}
                    element={
                        <BaseLayout/>
                    }
                />
            }
        },
    },
}