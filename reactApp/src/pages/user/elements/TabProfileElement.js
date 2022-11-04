import * as React from 'react';
import {useCallback, useContext, useEffect, useRef} from 'react';
import {
    Avatar,
    Box,
    Button,
    Card,
    CardActionArea,
    CardContent,
    CardMedia,
    CircularProgress,
    FormGroup,
    Grid,
    Stack,
    TextField,
    Typography
} from "@mui/material";
import {ConstantKMM, ConstantLottie, LanguageContext, useLocalStorage} from "../../../base";
import {TabBarElement} from "./TabBarElement";
import {ErrorElement} from "./ErrorElement";
import {LoadingElement} from "./LoadingElement";
import Lottie from "lottie-react";
import PropTypes from "prop-types";
import {Group, GroupAdd, HomeRepairService} from "@mui/icons-material";
import {EditDialogElement} from "./EditDialogElement";
import {Formik} from "formik";
import * as Yup from "yup";
import shared from "shared";
import {AlertError, AlertSuccess} from "../../../components";
import {ValueType} from "../../../base/route/ValueType";

let timeoutID

export function TabProfileElement(props) {

    const {t} = useContext(LanguageContext)
    const darkMode = useLocalStorage("darkMode", ValueType.bool);

    const {
        models = [],
        user = null,
        updateModels = function (followers) {
        },
        updateUser = function (followers) {
        }
    } = props

    const itemsRef = useRef()
    const loadingRef = useRef()

    const modelsCard = []

    const [page, setPage] = React.useState(1);
    const [error, setError] = React.useState(null);
    const [end, setEnd] = React.useState(false);
    const [loading, setLoading] = React.useState(models.length === 0 || user === null);
    const [loadingPage, setLoadingPage] = React.useState(false);
    const [data, setData] = React.useState(models);
    const [modelUser, setModelUser] = React.useState(user);
    const [openEditDialog, setOpenEditDialog] = React.useState(false);

    useEffect(() => {
        clearTimeout(timeoutID)
        timeoutID = setTimeout(() => {

            if (loading || loadingPage) {
                ConstantKMM.httpClient.get.followers(page).then(async (response) => {
                    const result = response.toArray()

                    if (result.length < ConstantKMM.appConstants.APP.PAGE_LIMIT || result.length === 0) {
                        setEnd(true)
                    }

                    if (loading) {

                        ConstantKMM.httpClient.get.user().then(async (user) => {
                            setLoading(false)
                            setData(result)
                            setModelUser(user)
                        }).catch(async (error) => {
                            setModelUser(null)
                            setLoadingPage(false)
                            setLoading(false)
                            setError(error)
                        });

                    } else {
                        setLoadingPage(false)
                        setData(data.concat(result))
                    }

                }).catch(async (error) => {
                    setLoadingPage(false)
                    setLoading(false)
                    setError(error)
                });
            }
        }, 500)
    }, [data, loading, loadingPage, page])

    // The scroll listener
    const handleScrollFollower = useCallback((event) => {
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
            div.addEventListener("scroll", handleScrollFollower)
        }
    }, [handleScrollFollower, loading, data])

    useEffect(() => {
        updateModels(data)
    }, [data, updateModels])

    useEffect(() => {
        updateUser(modelUser)
    }, [modelUser, updateUser])

    data.forEach((model, index) => {
        modelsCard.push(
            <ItemFollower
                key={`repo-item-${index}`}
                model={model}
            />
        )
    })

    return (
        <>
            <EditDialogElement
                title={t('common.menu_edit_profile')}
                open={openEditDialog}
                onClose={() => {
                    setOpenEditDialog(false)
                }}
            >
                <EditForm
                    modelUser={modelUser}
                    onChangeModel={(model) => {
                        setModelUser(model)
                    }}
                />
            </EditDialogElement>

            <Stack sx={{
                width: '100%',
                height: '100%'
            }}>
                <TabBarElement
                    title={""}
                    loading={loading}
                    onRefreshClick={() => {
                        setTimeout(() => {
                            setPage(1)
                            setLoading(true)
                        }, 350)
                    }}
                    onSettingsClick={() => {

                    }}
                    editTitle={t('common.menu_edit_profile')}
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

                        <Grid className={darkMode ? 'sectionDark' : 'section'} item xs={8} sx={{
                            height: '100%',
                            paddingX: 2
                        }}>
                            <Typography gutterBottom variant="h6" component="div" color='text.primary' sx={{
                                paddingTop: 2
                            }}>
                                {t('profile.title_profile')}
                            </Typography>

                            <PageUser
                                model={modelUser}
                            />
                        </Grid>

                        <Grid className={darkMode ? 'sectionDark' : 'section'} ref={itemsRef} item xs={4} sx={{
                            p: 2,
                            height: '100%',
                        }}>
                            <Stack
                                spacing={2}
                            >
                                <Typography gutterBottom variant="h6" color='text.primary' sx={{
                                    marginBottom: '-16px'
                                }}>
                                    {t('profile.title_follower')}
                                </Typography>

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
                    </Grid>
                )}
            </Stack>
        </>
    )
}

TabProfileElement.propTypes = {
    user: PropTypes.object,
    models: PropTypes.array.isRequired,
    updateModels: PropTypes.func.isRequired,
    updateUser: PropTypes.func.isRequired
};

function EditForm(props) {

    const {t} = useContext(LanguageContext)

    const [submitLoader, setSubmitLoader] = React.useState(false);

    const {
        modelUser,
        onChangeModel
    } = props

    return (
        <Stack sx={{
            p: 3
        }}>
            <Formik
                initialValues={{
                    name: modelUser.name ?? "",
                    blog: modelUser.blog ?? "",
                    twitterUsername: modelUser.twitterUsername ?? "",
                    company: modelUser.company ?? "",
                    location: modelUser.location ?? "",
                    bio: modelUser.bio ?? "",
                    submit: null
                }}
                validationSchema={Yup.object().shape({
                    name: Yup.string().required(t('profile.edit.form_name_is_required')),
                })}
                onSubmit={async (values, {setErrors, setStatus, setSubmitting}) => {
                    setSubmitLoader(true);
                    setStatus({success: null});
                    setErrors({submit: null});

                    try {

                        await new Promise(r => setTimeout(r, 1000));

                        const response = await ConstantKMM.httpClient.patch.updateUser(
                            new shared.com.keygenqt.viewer.data.requests.UserRequest(
                                values.name,
                                values.blog,
                                values.twitterUsername,
                                values.company,
                                values.location,
                                values.bio,
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
                      values
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
                                        label={t('profile.edit.form_name_label')}
                                        variant="filled"
                                    />
                                </Grid>

                                <Grid item xs={12}>
                                    <TextField
                                        disabled={isSubmitting}
                                        type={'text'}
                                        name={'blog'}
                                        value={values.blog}
                                        helperText={touched.blog ? errors.blog : ''}
                                        error={Boolean(touched.blog && errors.blog)}
                                        onBlur={handleBlur}
                                        onChange={handleChange}
                                        fullWidth
                                        label={t('profile.edit.form_blog_label')}
                                        variant="filled"
                                    />
                                </Grid>

                                <Grid item xs={12}>
                                    <TextField
                                        disabled={isSubmitting}
                                        type={'text'}
                                        name={'twitterUsername'}
                                        value={values.twitterUsername}
                                        helperText={touched.twitterUsername ? errors.twitterUsername : ''}
                                        error={Boolean(touched.twitterUsername && errors.twitterUsername)}
                                        onBlur={handleBlur}
                                        onChange={handleChange}
                                        fullWidth
                                        label={t('profile.edit.form_twitter_label')}
                                        variant="filled"
                                    />
                                </Grid>

                                <Grid item xs={12}>
                                    <TextField
                                        disabled={isSubmitting}
                                        type={'text'}
                                        name={'company'}
                                        value={values.company}
                                        helperText={touched.company ? errors.company : ''}
                                        error={Boolean(touched.company && errors.company)}
                                        onBlur={handleBlur}
                                        onChange={handleChange}
                                        fullWidth
                                        label={t('profile.edit.form_company_label')}
                                        variant="filled"
                                    />
                                </Grid>

                                <Grid item xs={12}>
                                    <TextField
                                        disabled={isSubmitting}
                                        type={'text'}
                                        name={'location'}
                                        value={values.location}
                                        helperText={touched.location ? errors.location : ''}
                                        error={Boolean(touched.location && errors.location)}
                                        onBlur={handleBlur}
                                        onChange={handleChange}
                                        fullWidth
                                        label={t('profile.edit.form_location_label')}
                                        variant="filled"
                                    />
                                </Grid>

                                <Grid item xs={12}>
                                    <TextField
                                        disabled={isSubmitting}
                                        type={'text'}
                                        name={'bio'}
                                        value={values.bio}
                                        helperText={touched.bio ? errors.bio : ''}
                                        error={Boolean(touched.bio && errors.bio)}
                                        onBlur={handleBlur}
                                        onChange={handleChange}
                                        fullWidth
                                        multiline
                                        minRows={4}
                                        maxRows={10}
                                        label={t('profile.edit.form_bio_label')}
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
    modelUser: PropTypes.object.isRequired,
    onChangeModel: PropTypes.func.isRequired
};

function ItemFollower(props) {

    const {
        model,
    } = props

    return (
        <>
            <Box sx={{height: '5px'}}/>
            <Card variant="outlined" sx={{
                overflow: 'inherit',
                position: 'relative',
                transitionDuration: '300ms',
                transitionTimingFunction: "ease-in-out",
                transitionProperty: 'border',
            }}>
                <CardActionArea onClick={() => {
                    window.open(model.htmlUrl, '_blank')
                }}>
                    <CardMedia
                        component="img"
                        height="200"
                        image={model.avatarUrl}
                        alt="green iguana"
                        sx={{
                            position: 'absolute',
                            width: '40px',
                            height: '40px',
                            borderRadius: '50%',
                            left: 0,
                            right: 0,
                            top: '-20px',
                            boxShadow: 'rgb(0 0 0 / 10%) 0px 3px 5px -1px, rgb(0 0 0 / 4%) 0px 5px 8px 0px, rgb(0 0 0 / 2%) 0px 1px 14px 0px',
                            margin: 'auto',
                            zIndex: 99
                        }}
                    />
                    <CardContent>
                        <Typography gutterBottom variant="h5" component="div" sx={{
                            paddingTop: '8px',
                            textTransform: 'capitalize'
                        }}>
                            {model.login}
                        </Typography>
                    </CardContent>
                </CardActionArea>
            </Card>
        </>
    )
}

ItemFollower.propTypes = {
    model: PropTypes.object.isRequired,
};

function PageUser(props) {

    const {model} = props

    const {isLocEn, t} = useContext(LanguageContext)

    return (
        <Stack>
            <Box align={"center"} sx={{
                paddingTop: '13px',
                paddingBottom: '7px',
            }}>
                <Avatar sx={{
                    width: '100%',
                    borderRadius: '50px',
                    height: 340,
                    boxShadow: 'rgb(0 0 0 / 10%) 0px 3px 5px -1px, rgb(0 0 0 / 4%) 0px 5px 8px 0px, rgb(0 0 0 / 2%) 0px 1px 14px 0px',
                }} src={model.avatarUrl} alt={'Logo'}/>
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
                        {t('profile.block_public_repos')}
                    </Typography>
                    <Box sx={{
                        backgroundColor: 'secondary.main',
                        borderRadius: '26px',
                        paddingX: '28px',
                        paddingTop: '8px',
                        paddingBottom: '5px'
                    }}>
                        <HomeRepairService fontSize={'large'} sx={{color: 'text.primary'}}/>
                    </Box>
                    <Typography variant="h6" color='text.primary'>
                        {model.publicRepos}
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
                        {t('profile.block_followers')}
                    </Typography>
                    <Box sx={{
                        backgroundColor: 'secondary.main',
                        borderRadius: '26px',
                        paddingX: '28px',
                        paddingTop: '8px',
                        paddingBottom: '5px'
                    }}>
                        <Group fontSize={'large'} sx={{color: 'text.primary'}}/>
                    </Box>
                    <Typography variant="h6" color='text.primary'>
                        {model.followers}
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
                        {t('profile.block_following')}
                    </Typography>
                    <Box sx={{
                        backgroundColor: 'secondary.main',
                        borderRadius: '26px',
                        paddingX: '28px',
                        paddingTop: '8px',
                        paddingBottom: '5px'
                    }}>
                        <GroupAdd fontSize={'large'} sx={{color: 'text.primary'}}/>
                    </Box>
                    <Typography variant="h6" color='text.primary'>
                        {model.following}
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
                {model.company ? (
                    <Stack
                        spacing={1}
                    >
                        <Typography variant="h6" color='text.primary'>
                            {t('profile.label_company')}
                        </Typography>
                        <Typography variant="body1" color='text.primary'>
                            {model.company}
                        </Typography>
                    </Stack>
                ) : null}

                {model.blog ? (
                    <Stack
                        spacing={1}
                    >
                        <Typography variant="h6" color='text.primary'>
                            {t('profile.label_blog')}
                        </Typography>
                        <Typography variant="body1" color='text.primary'>
                            {model.blog}
                        </Typography>
                    </Stack>
                ) : null}

                {model.location ? (
                    <Stack
                        spacing={1}
                    >
                        <Typography variant="h6" color='text.primary'>
                            {t('profile.label_location')}
                        </Typography>
                        <Typography variant="body1" color='text.primary'>
                            {model.location}
                        </Typography>
                    </Stack>
                ) : null}

                <Stack
                    spacing={1}
                >
                    <Typography variant="h6" color='text.primary'>
                        {t('profile.label_created_at')}
                    </Typography>
                    <Typography variant="body1" color='text.primary'>
                        {ConstantKMM.platformHelper.dateFormat(
                            ConstantKMM.platformHelper.toTimestamp(model.createdAt),
                            isLocEn ? "en-EN" : "ru-RU"
                        )}
                    </Typography>
                </Stack>

                {model.bio ? (
                    <Stack
                        spacing={1}
                    >
                        <Typography variant="h6" color='text.primary'>
                            {t('profile.label_bio')}
                        </Typography>
                        <Typography variant="body1" color='text.primary'>
                            {model.bio}
                        </Typography>
                    </Stack>
                ) : null}

            </Stack>
        </Stack>
    )
}

PageUser.propTypes = {
    model: PropTypes.object.isRequired,
};