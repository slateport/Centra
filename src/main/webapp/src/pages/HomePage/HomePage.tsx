import React from 'react';
import {connect} from 'react-redux';
import {Helmet} from 'react-helmet';
import Init from "../../domain/Init";
import styled from "styled-components";
import {
    Button,
    Card as MuiCard,
    CardActions,
    CardContent as MuiCardContent,
    CardMedia as MuiCardMedia,
    Divider as MuiDivider,
    Grid,
    Typography as MuiTypography,
} from "@material-ui/core";
import {spacing, SpacingProps} from "@material-ui/system";

import {project as projectService} from '../../services'
import {NewIssueButton} from "../../components/header/NewIssueButton";

interface TypographyPropsType extends SpacingProps {
    component?: string
}
const Typography = styled(MuiTypography)<TypographyPropsType>(spacing);

const Card = styled(MuiCard)(spacing);

const CardContent = styled(MuiCardContent)`
  border-bottom: 1px solid ${props => props.theme.palette.grey[300]};
`;

const CardMedia = styled(MuiCardMedia)`
  height: 220px;
`;

const Divider = styled(MuiDivider)(spacing);

interface IHomePageProps {
    init: Init
}

class HomePage extends React.Component<IHomePageProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            projectList: []
        }
    }

    componentDidMount() {
        projectService.getAllProjects().then(
            r => r.json()
                .then(projectList => this.setState({ projectList }))
        )
    }

    render() {
        return (
            <React.Fragment>
                <Helmet title="Projects" />

                <Typography variant="h3" gutterBottom display="inline">
                    Projects
                </Typography>

                <Divider my={6} />

                <Grid container spacing={6}>
                    <Grid item xs={12} lg={6} xl={3}>
                        {this.state.projectList.map(project =>
                            <Project
                                key={project.id}
                                id={project.projectKey}
                                title={project.projectName}
                                description={project.description}
                            />
                        )}

                    </Grid>
                </Grid>
            </React.Fragment>
        );
    }
}

type ProjectPropsType = {
    image?: string
    title: string
    description: string
    id: string
}

const Project: React.FC<ProjectPropsType> = ({ image, title, description, id }) => {
    return (
        <Card mb={6}>
            {image ? <CardMedia image={image} title="Contemplative Reptile" /> : null}
            <CardContent>
                <Typography gutterBottom variant="h5" component="h2">
                    {title}
                </Typography>
                <Typography mb={4} component="p">
                    {description}
                </Typography>
            </CardContent>
            <CardActions>
                <Button size="small" color="primary" href={"/search?cql=projectKey="+id}>
                    View Issues
                </Button>
                <NewIssueButton projectId={id}/>
            </CardActions>
        </Card>
    );
}

export { HomePage }


