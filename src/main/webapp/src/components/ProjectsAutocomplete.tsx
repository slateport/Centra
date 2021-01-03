import styled from "styled-components";
import CloseIcon from "@material-ui/icons/Close";
import React from "react";
import {Autocomplete, useAutocomplete} from "@material-ui/lab";
import {issue as issueService, project} from "../services";
import CheckIcon from "@material-ui/icons/Check";
import {TextField} from "@material-ui/core";

const Tag = styled(({ label, onDelete, ...props }) => {
    return (
        <div {...props}>
            <span>{label}</span>
            <CloseIcon onClick={onDelete} />
        </div>
    )
})`
  display: flex;
  align-items: center;
  height: 24px;
  margin: 2px;
  line-height: 22px;
  background-color: rgba(25, 118, 210, 0.04);
  box-sizing: content-box;
  padding: 0 4px 0 10px;
  outline: 0;
  overflow: hidden;
  color: #1976d2;

  &:focus {
    border-color: #40a9ff;
    background-color: #e6f7ff;
  }

  & span {
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  }

  & svg {
    font-size: 12px;
    cursor: pointer;
    padding: 4px;
  }
`;

const InputWrapper = styled('div')`
  background-color: #fff;
  padding: 1px;
  display: flex;
  flex-wrap: wrap;
  border: 1px solid #d9d9d9;
  border-radius: 4px;

  &:hover {
    border-color: #40a9ff;
  }

  &.focused {
    border-color: #40a9ff;
    box-shadow: 0 0 0 2px rgba(24, 144, 255, 0.2);
  }

  & input {
    font-size: 14px;
    height: 30px;
    box-sizing: border-box;
    padding: 4px 6px;
    width: 0;
    min-width: 30px;
    flex-grow: 1;
    border: 0;
    margin: 0;
    outline: 0;
  }
`;

const Listbox = styled('ul')`
  margin: 2px 0 0;
  padding: 0;
  position: absolute;
  list-style: none;
  background-color: #fff;
  overflow: auto;
  max-height: 250px;
  border-radius: 4px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  z-index: 1;

  & li {
    padding: 5px 12px;
    display: flex;

    & span {
      flex-grow: 1;
    }

    & svg {
      color: transparent;
    }
  }

  & li[aria-selected='true'] {
    background-color: #fafafa;
    font-weight: 600;

    & svg {
      color: #1890ff;
    }
  }

  & li[data-focus='true'] {
    background-color: #e6f7ff;
    cursor: pointer;

    & svg {
      color: #000;
    }
  }
`;

const ProjectsAutocomplete = ({onChangeFunction}) => {
    const [projects, setProjects] = React.useState<any[]>([]);
    const loadingProjects = projects.length === 0;

    React.useEffect(() => {
        let active = true;

        if (!loadingProjects) {
            return undefined;
        }

        (async () => {
            await project.getAllProjects()
                .then(response => setProjects(response))
        })();

        return () => {
            active = false;
        };
    }, [loadingProjects]);

    return (
        <Autocomplete
            multiple
            options={projects}
            fullWidth
            getOptionLabel={(option) => option.projectName}
            filterSelectedOptions
            renderInput={(params) => (
                <TextField {...params} variant="outlined"  name={"projects"}/>
            )}
            onChange={(_, value) => onChangeFunction(value)}
        />
    )
}

export default ProjectsAutocomplete