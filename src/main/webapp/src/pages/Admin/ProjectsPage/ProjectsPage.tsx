import {connect} from "react-redux";
import React from "react";
import Init from "../../../domain/Init";
import {
    Button,
    Card as MuiCard,
    CardContent,
    Divider as MuiDivider,
    Table,
    TableBody,
    TableContainer,
    Typography
} from "@material-ui/core";
import {Helmet} from "react-helmet";
import styled from "styled-components";
import {spacing} from "@material-ui/system";
import {project as projectService} from '../../../services'
import TableRow from "@material-ui/core/TableRow";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import {NewProjectButton} from "./NewProjectButton";
import {DeleteProjectLink} from "./DeleteProjectLink";

interface IProjectsPageProps {
    init: Init
}

const Card = styled(MuiCard)(spacing);
const Divider = styled(MuiDivider)(spacing);

class ProjectsPage extends React.Component<IProjectsPageProps, any> {

    constructor(props) {
        super(props);

        this.state = {
            projectList: []
        }
    }

    componentDidMount() {
        projectService.getAllProjects().then(r => r.json().then(projectList => this.setState({projectList})))
    }

    render() {
        if (!this.props.init.user?.admin) {
            return(
                <Typography>You are not authorised to view this page.</Typography>
            )
        }

        return (
            <React.Fragment>
                <Helmet title="Projects" />

                <Typography variant="h3" gutterBottom display="inline">
                    Manage Projects
                </Typography>

                <Divider my={6} />

                <Card>
                    <CardContent>
                        <NewProjectButton />
                        <TableContainer>
                            <Table>
                                <TableHead>
                                    <TableRow>
                                        <TableCell>Key</TableCell>
                                        <TableCell>Name</TableCell>
                                        <TableCell>Description</TableCell>
                                        <TableCell>Actions</TableCell>
                                    </TableRow>
                                </TableHead>

                                <TableBody>
                                    {this.state.projectList.map((project) => (
                                     <TableRow key={project.id}>
                                        <TableCell>{project.projectKey}</TableCell>
                                        <TableCell>{project.projectName}</TableCell>
                                        <TableCell>{project.description}</TableCell>
                                         <TableCell>
                                             <DeleteProjectLink project={project} />
                                         </TableCell>
                                     </TableRow>
                                    ))}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </CardContent>
                </Card>
            </React.Fragment>
        )


    }
}

function mapStateToProps(state) {
    const { init } = state
    return { init };
}

const connectedProjectsPage = connect(mapStateToProps)(ProjectsPage);
export { connectedProjectsPage as ProjectsPage };