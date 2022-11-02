import * as React from 'react';
import {AppBar, Box, Button, FormGroup, Grid, IconButton, Stack, TextField, Toolbar, Typography} from "@mui/material";
import {ArrowBack} from "@mui/icons-material";
import {useContext} from "react";
import {ConstantKMM, NavigateContext} from "../../base";
import {Formik} from "formik";
import * as Yup from "yup";
import {AlertError, AlertSuccess} from "../../components";

export function SignInPage() {

    const {route} = useContext(NavigateContext)

    let uuid = require("uuid");

    return (
        <Box sx={{
            flexGrow: 1,
            '&:after': {
                content: '" "',
                position: 'absolute',
                width: 300,
                height: 300,
                background: '#d8c7ff',
                borderRadius: '50%',
                left: -100,
                bottom: -175
            }
        }}>
            <AppBar position="static" color={"secondary"} elevation={0}>
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                        onClick={() => {
                            route.toBack()
                        }}
                    >
                        <ArrowBack />
                    </IconButton>

                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        Sign In
                    </Typography>
                </Toolbar>
            </AppBar>

            <Stack sx={{
                p: 3
            }}>
                <Formik
                    initialValues={{
                        nickname: "",
                        submit: null
                    }}
                    validationSchema={Yup.object().shape({
                        nickname: Yup.string().required('Nickname is required'),
                    })}
                    onSubmit={async (values, {setErrors, setStatus, setSubmitting}) => {
                        window.open(ConstantKMM.appHelper.getOauthLink(values.nickname, uuid.v4()), '_blank')
                    }}
                >
                    {({
                          status,
                          errors,
                          handleBlur,
                          handleChange,
                          handleSubmit,
                          isSubmitting,
                          touched,
                          values,
                      }) => (
                        <form noValidate onSubmit={handleSubmit}>

                            {errors.submit && (
                                <AlertError>
                                    {errors.submit}
                                </AlertError>
                            )}

                            {status && status.success && (
                                <AlertSuccess>
                                    Success submit form!
                                </AlertSuccess>
                            )}

                            <FormGroup>
                                <Grid container spacing={2}>

                                    <Grid item xs={12}>
                                        <TextField
                                            disabled={isSubmitting}
                                            type={'text'}
                                            name={'nickname'}
                                            value={values.nickname}
                                            helperText={touched.nickname ? errors.nickname : ''}
                                            error={Boolean(touched.nickname && errors.nickname)}
                                            onBlur={handleBlur}
                                            onChange={handleChange}
                                            fullWidth
                                            label="Nickname"
                                            variant="filled"
                                        />
                                    </Grid>

                                    <Grid item xs={12} sx={{
                                        textAlign: 'end'
                                    }}>
                                        <Button
                                            variant={"contained"}
                                            disabled={isSubmitting}
                                            type="submit"
                                            size={'medium'}
                                        >
                                            Submit
                                        </Button>
                                    </Grid>
                                </Grid>
                            </FormGroup>
                        </form>
                    )}
                </Formik>
            </Stack>
        </Box>
    );
}

SignInPage.propTypes = {};