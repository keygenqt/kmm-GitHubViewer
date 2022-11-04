import * as React from 'react';
import {useCallback, useContext, useEffect, useRef} from 'react';
import {
    Avatar,
    Box,
    Button,
    Card,
    CardActionArea,
    CardActions,
    CardContent,
    CircularProgress,
    FormControlLabel,
    FormGroup,
    Grid,
    Stack,
    Switch,
    TextField,
    Typography,
    useTheme
} from "@mui/material";
import {ConstantImages, ConstantKMM, ConstantLottie, LanguageContext, useLocalStorage} from "../../../base";
import {TabBarElement} from "./TabBarElement";
import {LoadingElement} from "./LoadingElement";
import {BugReport, CallSplit, Lock, LockOpen, Star, Storage, Visibility} from "@mui/icons-material";
import Lottie from "lottie-react";
import {ErrorElement} from "./ErrorElement";
import PropTypes from "prop-types";
import {EditDialogElement} from "./EditDialogElement";
import {Formik} from "formik";
import * as Yup from "yup";
import {AlertError, AlertSuccess} from "../../../components";
import shared from "shared";
import {ValueType} from "../../../base/route/ValueType";

let timeoutID

export function TabReposElement(props) {

    const {
        models = [],
        updateModels = function (repos) {
        }
    } = props

    const itemsRef = useRef()
    const loadingRef = useRef()
    const darkMode = useLocalStorage("darkMode", ValueType.bool);

    const modelsCard = []

    const {t} = useContext(LanguageContext)
    const [page, setPage] = React.useState(1);
    const [error, setError] = React.useState(null);
    const [end, setEnd] = React.useState(false);
    const [loading, setLoading] = React.useState(models.length === 0);
    const [loadingPage, setLoadingPage] = React.useState(false);
    const [isOrderASC, setIsOrderASC] = React.useState(ConstantKMM.crossStorage.isRepoOrder ?? false);
    const [data, setData] = React.useState(models);
    const [modelAction, setModelAction] = React.useState(models.length === 0 ? null : models[0]);
    const [openEditDialog, setOpenEditDialog] = React.useState(false);

    useEffect(() => {
        clearTimeout(timeoutID)
        timeoutID = setTimeout(() => {
            if (loading || loadingPage) {
                ConstantKMM.httpClient.get.repos(page, isOrderASC).then(async (response) => {
                    const result = response.toArray()

                    if (result.length < ConstantKMM.appConstants.APP.PAGE_LIMIT || result.length === 0) {
                        setEnd(true)
                    }

                    if (loading) {
                        setLoading(false)
                        setData(result)
                        setModelAction(result[0])
                    } else {
                        setLoadingPage(false)
                        setData(data.concat(result))
                    }
                }).catch(async (response) => {
                    setLoadingPage(false)
                    setLoading(false)
                    setError(response)
                });
            }
        }, 500)
    }, [data, isOrderASC, loading, loadingPage, page])

    // The scroll listener
    const handleScrollRepo = useCallback((event) => {
        if (!loadingPage) {
            const scrollEl = event.target
            const loadingEl = loadingRef.current

            if (scrollEl !== undefined && loadingEl !== undefined && loadingEl !== null) {
                if ((scrollEl.scrollTop + scrollEl.clientHeight) > ((loadingEl.offsetTop) - (loadingEl.clientHeight + 16))) {
                    setLoadingPage(true)
                    setPage(page + 1)
                }
            }
        }
    }, [loadingPage, page])

    useEffect(() => {
        if (itemsRef.current) {
            const div = itemsRef.current
            div.addEventListener("scroll", handleScrollRepo)
        }
    }, [handleScrollRepo, loading, data])

    useEffect(() => {
        updateModels(data)
    }, [data, updateModels])

    data.forEach((model, index) => {
        modelsCard.push(
            <ItemRepo
                key={`repo-item-${index}`}
                model={model}
                modelAction={modelAction}
                onChangeModel={(model) => {
                    setModelAction(model)
                }}
            />
        )
    })

    return (
        <>
            <EditDialogElement
                title={t('common.menu_edit_repo')}
                open={openEditDialog}
                onClose={() => {
                    setOpenEditDialog(false)
                }}
            >
                <EditForm
                    modelAction={modelAction}
                    onChangeModel={(model) => {
                        let changeIndex = null
                        for (let index = 0; index < data.length; index++) {
                            if (data[index].id === model.id) {
                                changeIndex = index
                                break;
                            }
                        }
                        if (changeIndex !== null) {
                            data[changeIndex] = model
                            setData(data)
                            setModelAction(model)
                        }
                    }}
                />
            </EditDialogElement>

            <Stack sx={{
                width: '100%',
                height: '100%'
            }}>
                <TabBarElement
                    title={t('repos.title')}
                    loading={loading}
                    isOrderASC={isOrderASC}
                    onSortClick={() => {
                        const result = !isOrderASC
                        setIsOrderASC(result)
                        setTimeout(() => {
                            ConstantKMM.crossStorage.isRepoOrder = result
                            setPage(1)
                            setLoading(true)
                        }, 350)
                    }}
                    onRefreshClick={() => {
                        setTimeout(() => {
                            setPage(1)
                            setLoading(true)
                        }, 350)
                    }}
                    onSettingsClick={() => {
                        console.log("ye")
                    }}
                    editTitle={t('common.menu_edit_repo')}
                    editOnClick={() => {
                        setOpenEditDialog(true)
                    }}
                />

                {loading || error ? (
                    error ? (
                        <ErrorElement
                            error={error}
                        />
                    ) : (
                        <LoadingElement/>
                    )
                ) : (
                    <Grid container spacing={0} sx={{
                        height: 'calc(100% - 64px)'
                    }}>
                        <Grid className={darkMode ? 'sectionDark' : 'section'} ref={itemsRef} item xs={4} sx={{
                            p: 2,
                            height: '100%',
                        }}>
                            <Stack
                                spacing={2}
                            >
                                {modelsCard}

                                {end ? null : (
                                    <Box ref={loadingRef}>
                                        <Lottie style={{
                                            width: 60,
                                            margin: 'auto',
                                        }} animationData={ConstantLottie.loader}/>
                                    </Box>
                                )}

                            </Stack>
                        </Grid>
                        <Grid className={darkMode ? 'sectionDark' : 'section'} item xs={8} sx={{
                            height: '100%',
                            paddingX: 2
                        }}>
                            <PageRepo
                                modelAction={modelAction}
                            />
                        </Grid>
                    </Grid>
                )}
            </Stack>
        </>
    )
}

TabReposElement.propTypes = {
    models: PropTypes.array.isRequired,
    updateModels: PropTypes.func.isRequired
};

function EditForm(props) {

    const {t} = useContext(LanguageContext)

    const [submitLoader, setSubmitLoader] = React.useState(false);

    const {
        modelAction,
        onChangeModel
    } = props

    return (
        <Stack sx={{
            p: 3
        }}>
            <Formik
                initialValues={{
                    name: modelAction.name,
                    description: modelAction.desc,
                    isPrivate: modelAction.isPrivate,
                    submit: null
                }}
                validationSchema={Yup.object().shape({
                    name: Yup.string().required(t('repos.edit.form_name_is_required')),
                    description: Yup.string().required(t('repos.edit.form_description_is_required')),
                })}
                onSubmit={async (values, {setErrors, setStatus, setSubmitting}) => {
                    setSubmitLoader(true);
                    setStatus({success: null});
                    setErrors({submit: null});

                    try {

                        await new Promise(r => setTimeout(r, 1000));

                        const response = await ConstantKMM.httpClient.patch.updateRepo(
                            modelAction.url,
                            new shared.com.keygenqt.viewer.data.requests.RepoRequest(
                                values.name,
                                values.description,
                                values.isPrivate,
                            )
                        )

                        onChangeModel(response)

                        setStatus({success: true});
                        setSubmitLoader(false);
                        setSubmitting(false);

                    } catch (error) {
                        setStatus({success: false});
                        setSubmitLoader(false);
                        setSubmitting(false);
                    }
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
                      setFieldValue
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
                                        name={'name'}
                                        value={values.name}
                                        helperText={touched.name ? errors.name : ''}
                                        error={Boolean(touched.name && errors.name)}
                                        onBlur={handleBlur}
                                        onChange={handleChange}
                                        fullWidth
                                        label={t('repos.edit.form_name_label')}
                                        variant="filled"
                                    />
                                </Grid>

                                <Grid item xs={12}>
                                    <TextField
                                        disabled={isSubmitting}
                                        type={'text'}
                                        name={'description'}
                                        value={values.description}
                                        helperText={touched.description ? errors.description : ''}
                                        error={Boolean(touched.description && errors.description)}
                                        onBlur={handleBlur}
                                        onChange={handleChange}
                                        fullWidth
                                        multiline
                                        minRows={4}
                                        maxRows={10}
                                        label={t('repos.edit.form_description_label')}
                                        variant="filled"
                                    />
                                </Grid>

                                <Grid item xs={12}>
                                    <FormControlLabel
                                        sx={{
                                            color: errors.isPrivate ? '#d32f2f' : 'auto'
                                        }}
                                        control={<Switch
                                            disabled={isSubmitting}
                                            checked={values.isPrivate}
                                            onChange={(event, checked) => setFieldValue('isPrivate', checked)}
                                        />}
                                        label={t('repos.edit.form_is_private') + (errors.isPrivate ? ` (${errors.isPrivate})` : '')}
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
                                        <Stack
                                            direction="row"
                                            justifyContent="center"
                                            alignItems="center"
                                            spacing={2}
                                        >
                                            {submitLoader ? (
                                                <CircularProgress size={'16px'}/>
                                            ) : null}

                                            <Box>
                                                {t('common.form_btn_save')}
                                            </Box>

                                        </Stack>
                                    </Button>
                                </Grid>
                            </Grid>
                        </FormGroup>
                    </form>
                )}
            </Formik>
        </Stack>
    )
}

EditForm.EditForm = {
    modelAction: PropTypes.object.isRequired,
    onChangeModel: PropTypes.func.isRequired
};

function ItemRepo(props) {

    const theme = useTheme()

    const {
        model,
        modelAction,
        onChangeModel,
    } = props

    return (
        <Card variant="outlined" sx={{
            transitionDuration: '300ms',
            transitionTimingFunction: "ease-in-out",
            transitionProperty: 'border',
            ...(model.id !== modelAction.id ? {} : {
                border: '1px solid ' + theme.palette.primary.light + '9e',
            })
        }}>
            <CardActionArea onClick={() => {
                onChangeModel(model)
            }}>
                <CardContent>
                    <Stack
                        direction="row"
                        alignItems="center"
                        spacing={1}
                        sx={{
                            paddingBottom: model.desc ? 2 : 0
                        }}
                    >
                        <Avatar aria-label="languages" sx={{
                            '&': {
                                border: '1px solid #eaeaea',
                                backgroundColor: "transparent",
                            },
                            '& svg': {
                                width: '24px',
                            }
                        }}>
                            {getIconLanguage(model.language)}
                        </Avatar>

                        <Typography gutterBottom variant="h5" component="div">
                            {model.name}
                        </Typography>
                    </Stack>

                    <Typography variant="body2" color="text.secondary">
                        {model.desc}
                    </Typography>
                </CardContent>

                <CardActions disableSpacing sx={{
                    paddingTop: 0,
                    paddingBottom: 2
                }}>
                    <Stack
                        direction="row"
                        justifyContent="space-between"
                        alignItems="center"
                        spacing={2}
                        sx={{
                            width: '100%'
                        }}
                    >

                        <Stack
                            direction="row"
                            alignItems="center"
                            spacing={2}
                            sx={{
                                paddingLeft: 1
                            }}
                        >
                            <Stack direction="row" spacing={1}>
                                <Star fontSize={'small'}/>
                                <Typography variant="body2" color="text.secondary">
                                    {model.stargazersCount}
                                </Typography>
                            </Stack>

                            <Stack direction="row" spacing={1}>
                                <CallSplit fontSize={'small'}/>
                                <Typography variant="body2" color="text.secondary">
                                    {model.forks}
                                </Typography>
                            </Stack>

                            <Stack direction="row" spacing={1}>
                                <Visibility fontSize={'small'}/>
                                <Typography variant="body2" color="text.secondary">
                                    {model.watchersCount}
                                </Typography>
                            </Stack>
                        </Stack>

                    </Stack>

                    {model.isPrivate ? (
                        <Lock fontSize={'small'}/>
                    ) : (
                        <LockOpen fontSize={'small'}/>
                    )}

                </CardActions>
            </CardActionArea>
        </Card>
    )
}

ItemRepo.propTypes = {
    model: PropTypes.object.isRequired,
    modelAction: PropTypes.object.isRequired,
    onChangeModel: PropTypes.func.isRequired
};

function PageRepo(props) {

    const theme = useTheme()
    const {isLocEn, t} = useContext(LanguageContext)

    const {modelAction} = props

    return (
        <>
            <Box sx={{
                padding: 2,
            }}>
                <Typography variant="h6" color='text.primary'>
                    {modelAction.fullName}
                </Typography>
            </Box>

            <Box sx={{
                overflow: 'hidden',
                borderRadius: '60px',
                boxShadow: 'rgb(0 0 0 / 10%) 0px 3px 5px -1px, rgb(0 0 0 / 4%) 0px 5px 8px 0px, rgb(0 0 0 / 2%) 0px 1px 14px 0px',
            }}>
                <Stack
                    justifyContent="center"
                    alignItems="flex-start"
                    spacing={1}
                    sx={{
                        width: '100%',
                        backgroundColor: 'secondary.main',
                        paddingX: 6,
                        paddingY: '25px',
                        boxSizing: 'border-box',
                        backgroundImage: `url(${ConstantImages.repos.chart_1})`,
                        backgroundSize: '100% 100%',
                        backgroundPosition: 'bottom',
                        position: 'relative',
                        zIndex: 1
                    }}
                >
                    <Stack
                        direction="row"
                        spacing={3}
                        justifyContent="center"
                        alignItems="center"
                        sx={{
                            paddingBottom: '50px'
                        }}
                    >
                        <Typography variant="h1">
                            {modelAction.stargazersCount}
                        </Typography>

                        <Stack
                            spacing={0}
                        >
                            <Typography variant="h6">
                                {t('repos.chart_1_title')}
                            </Typography>

                            <Typography variant="body1" sx={{paddingBottom: 1}}>
                                {t('repos.chart_1_subtitle')}
                            </Typography>
                        </Stack>
                    </Stack>

                </Stack>

                <Stack
                    justifyContent="center"
                    alignItems="flex-start"
                    spacing={1}
                    sx={{
                        width: '100%',
                        backgroundColor: 'primary.main',
                        borderBottomLeftRadius: '60px',
                        borderBottomRightRadius: '60px',
                        paddingX: 6,
                        paddingY: '14px',
                        boxSizing: 'border-box',
                        backgroundImage: `url(${ConstantImages.repos.chart_2})`,
                        backgroundSize: '100% 100%',
                        backgroundPosition: 'bottom',
                        position: 'relative',
                        '&:after': {
                            content: '" "',
                            position: 'absolute',
                            height: 100,
                            background: theme.palette.primary.main,
                            top: '-98px',
                            right: 0,
                            left: 0
                        }
                    }}
                >
                    <Stack
                        direction="row"
                        spacing={3}
                        justifyContent="center"
                        alignItems="center"
                        sx={{
                            paddingBottom: '50px',
                            color: 'white'
                        }}
                    >
                        <Typography variant="h1">
                            {modelAction.forks}
                        </Typography>

                        <Stack
                            spacing={0}
                        >
                            <Typography variant="h6">
                                {t('repos.chart_2_title')}
                            </Typography>

                            <Typography variant="body1" sx={{paddingBottom: 1}}>
                                {t('repos.chart_2_subtitle')}
                            </Typography>
                        </Stack>
                    </Stack>

                </Stack>
            </Box>

            <Stack
                direction="row"
                spacing={3}
                justifyContent="space-between"
                alignItems="center"
                sx={{
                    p: 3,
                    paddingTop: 4
                }}
            >
                <Stack
                    spacing={1}
                    justifyContent="center"
                    alignItems="center"
                >
                    <Typography variant="body1" color='text.primary' sx={{
                        textTransform: 'uppercase'
                    }}>
                        {t('repos.block_issue')}
                    </Typography>
                    <Box sx={{
                        backgroundColor: 'secondary.main',
                        borderRadius: '26px',
                        paddingX: '28px',
                        paddingTop: '8px',
                        paddingBottom: '5px'
                    }}>
                        <BugReport fontSize={'large'} sx={{color: 'text.primary'}}/>
                    </Box>
                    <Typography variant="h6" color='text.primary'>
                        {modelAction.openIssuesCount}
                    </Typography>
                </Stack>

                <Stack
                    spacing={1}
                    justifyContent="center"
                    alignItems="center"
                >
                    <Typography variant="body1" color='text.primary' sx={{
                        textTransform: 'uppercase'
                    }}>
                        {t('repos.block_watchers')}
                    </Typography>
                    <Box sx={{
                        backgroundColor: 'secondary.main',
                        borderRadius: '26px',
                        paddingX: '28px',
                        paddingTop: '8px',
                        paddingBottom: '5px'
                    }}>
                        <Visibility fontSize={'large'} sx={{color: 'text.primary'}}/>
                    </Box>
                    <Typography variant="h6" color='text.primary'>
                        {modelAction.watchersCount}
                    </Typography>
                </Stack>

                <Stack
                    spacing={1}
                    justifyContent="center"
                    alignItems="center"
                >
                    <Typography variant="body1" color='text.primary' sx={{
                        textTransform: 'uppercase'
                    }}>
                        {t('repos.block_size')}
                    </Typography>
                    <Box sx={{
                        backgroundColor: 'secondary.main',
                        borderRadius: '26px',
                        paddingX: '28px',
                        paddingTop: '8px',
                        paddingBottom: '5px'
                    }}>
                        <Storage fontSize={'large'} sx={{color: 'text.primary'}}/>
                    </Box>
                    <Typography variant="h6" color='text.primary'>
                        {ConstantKMM.appHelper.humanReadableByte(modelAction.size * 1024)}
                    </Typography>
                </Stack>
            </Stack>

            <Stack
                spacing={3}
                sx={{
                    backgroundColor: 'secondary.light',
                    borderRadius: '10px',
                    p: 3,
                    marginBottom: 3
                }}
            >
                <Stack
                    spacing={1}
                >
                    <Typography variant="h6" color='text.primary'>
                        {t('repos.label_visibility')}
                    </Typography>
                    <Typography variant="body1" color='text.primary'>
                        {modelAction.visibility.toUpperCase()}
                    </Typography>
                </Stack>

                <Stack
                    spacing={1}
                >
                    <Typography variant="h6" color='text.primary'>
                        {t('repos.label_owner')}
                    </Typography>
                    <Typography variant="body1" color='text.primary'>
                        {modelAction.owner.login}
                    </Typography>
                </Stack>

                <Stack
                    spacing={1}
                >
                    <Typography variant="h6" color='text.primary'>
                        {t('repos.label_updated_at')}
                    </Typography>
                    <Typography variant="body1" color='text.primary'>
                        {ConstantKMM.platformHelper.dateFormat(
                            ConstantKMM.platformHelper.toTimestamp(modelAction.updatedAt),
                            isLocEn ? "en-EN" : "ru-RU"
                        )}
                    </Typography>
                </Stack>

                <Stack
                    spacing={1}
                >
                    <Typography variant="h6" color='text.primary'>
                        {t('repos.label_created_at')}
                    </Typography>
                    <Typography variant="body1" color='text.primary'>
                        {ConstantKMM.platformHelper.dateFormat(
                            ConstantKMM.platformHelper.toTimestamp(modelAction.createdAt),
                            isLocEn ? "en-EN" : "ru-RU"
                        )}
                    </Typography>
                </Stack>

                <Stack
                    spacing={1}
                >
                    <Typography variant="h6" color='text.primary'>
                        {t('repos.label_description')}
                    </Typography>
                    <Typography variant="body1" color='text.primary'>
                        {modelAction.desc}
                    </Typography>
                </Stack>

            </Stack>
        </>
    )
}

PageRepo.propTypes = {
    modelAction: PropTypes.object.isRequired,
};

function getIconLanguage(language) {
    let Icon = ConstantImages.languages.bash
    if (!language) {
        return <Icon/>
    }
    switch (language.toLowerCase()) {
        case ConstantKMM.appConstants.LANGUAGE.SWIFT:
            Icon = ConstantImages.languages.swift
            break;
        case ConstantKMM.appConstants.LANGUAGE.BASH:
            Icon = ConstantImages.languages.bash
            break;
        case ConstantKMM.appConstants.LANGUAGE.C:
            Icon = ConstantImages.languages.c
            break;
        case ConstantKMM.appConstants.LANGUAGE.CPLUSPLUS:
            Icon = ConstantImages.languages.cplusplus
            break;
        case ConstantKMM.appConstants.LANGUAGE.DART:
            Icon = ConstantImages.languages.dart
            break;
        case ConstantKMM.appConstants.LANGUAGE.ELIXIR:
            Icon = ConstantImages.languages.elixir
            break;
        case ConstantKMM.appConstants.LANGUAGE.ERLANG:
            Icon = ConstantImages.languages.erlang
            break;
        case ConstantKMM.appConstants.LANGUAGE.GROOVY:
            Icon = ConstantImages.languages.groovy
            break;
        case ConstantKMM.appConstants.LANGUAGE.HASKELL:
            Icon = ConstantImages.languages.haskell
            break;
        case ConstantKMM.appConstants.LANGUAGE.JAVA:
            Icon = ConstantImages.languages.java
            break;
        case ConstantKMM.appConstants.LANGUAGE.JAVASCRIPT:
            Icon = ConstantImages.languages.javascript
            break;
        case ConstantKMM.appConstants.LANGUAGE.KOTLIN:
            Icon = ConstantImages.languages.kotlin
            break;
        case ConstantKMM.appConstants.LANGUAGE.PHP:
            Icon = ConstantImages.languages.php
            break;
        case ConstantKMM.appConstants.LANGUAGE.PYTHON:
            Icon = ConstantImages.languages.python
            break;
        case ConstantKMM.appConstants.LANGUAGE.RUBY:
            Icon = ConstantImages.languages.ruby
            break;
        case ConstantKMM.appConstants.LANGUAGE.RUST:
            Icon = ConstantImages.languages.rust
            break;
        case ConstantKMM.appConstants.LANGUAGE.SCALA:
            Icon = ConstantImages.languages.scala
            break;
        default:
            Icon = ConstantImages.languages.bash
    }
    return <Icon/>
}