import styled from "styled-components";
import CloseIcon from "@material-ui/icons/Close";
import React from "react";
import {useAutocomplete} from "@material-ui/lab";
import {issue as issueService} from "../../../services";
import CheckIcon from "@material-ui/icons/Check";


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



const LabelsField = ({currentLabels, onLabelChange, ...rest}) => {

    const handleOnLabelChange = (newLables: string[]) => {
        onLabelChange(newLables)
    }

    const [labels, setLabels] = React.useState<string[]>(null);
    const loadingLabels = labels == null;

    const {
        getRootProps,
        getInputLabelProps,
        getInputProps,
        getTagProps,
        getListboxProps,
        getOptionProps,
        groupedOptions,
        value,
        focused,
        setAnchorEl,
    } = useAutocomplete({
        id: 'labels',
        defaultValue: currentLabels,
        multiple: true,
        options: labels,
        autoSelect: true,
        freeSolo:true,
        getOptionLabel: (option) => option,
        getOptionSelected: (option, value) => option == value,
        onChange: (_, values) => handleOnLabelChange(values)
    });

    React.useEffect(() => {
        if (!loadingLabels) {
            return undefined
        }
        (async () => {
            await issueService.getAllLabels()
                .then(response => setLabels(response.data))
        })();
    })

    return (
        <React.Fragment>
            <div {...getRootProps()}>
                <InputWrapper ref={setAnchorEl} className={focused ? 'focused' : ''} {...rest}>
                    {value.map((option, index: number) => (
                        <Tag label={option} key={index} {...getTagProps({ index })} />
                    ))}
                    <input {...getInputProps()} />
                </InputWrapper>
            </div>
            {groupedOptions.length > 0 ? (
                <Listbox {...getListboxProps()}>
                    {groupedOptions.map((option, index) => (
                        <li key={index} {...getOptionProps({ option, index })}>
                            <span>{option}</span>
                            <CheckIcon fontSize="small" />
                        </li>
                    ))}
                </Listbox>
            ) : null}
        </React.Fragment>
    )
}

export default LabelsField