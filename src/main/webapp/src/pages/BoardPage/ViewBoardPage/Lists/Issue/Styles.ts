import styled, { css }  from "styled-components";
import {Link} from "react-router-dom";

export const IssueLink = styled(Link)`
  display: block;
  margin-bottom: 5px;
  text-decoration: none;
`;



export const IssueDiv = styled.div`
  padding: 10px;
  border-radius: 3px;
  background: #fff;
  box-shadow: 0px 1px 2px 0px rgba(9, 30, 66, 0.25);
  transition: background 0.1s;
  min-width: 324px;
  @media (max-width: 1100px) {
    padding: 10px 8px;
  }
  &:hover {
    background: #ebecf0;
  }
  ${props =>
    props.isBeingDragged &&
    css`
      transform: rotate(3deg);
      box-shadow: 5px 10px 30px 0px rgba(9, 30, 66, 0.15);
    `}
`;

export const Title = styled.p`
  padding-bottom: 11px;
  font-size: 15px;
  width: 100%;
  @media (max-width: 1100px) {
    font-size: 14.5px
  }
`;

export const Bottom = styled.div`
  display: flex;
  width: 100%;
  font-size: 12.5px;
`;