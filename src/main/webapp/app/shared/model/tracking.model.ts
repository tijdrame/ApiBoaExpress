import { Moment } from 'moment';

export interface ITracking {
  id?: number;
  codeResponse?: string;
  endPoint?: string;
  loginActeur?: string;
  requestId?: string;
  dateRequest?: Moment;
  dateResponse?: Moment;
  requestTr?: any;
  responseTr?: any;
}

export class Tracking implements ITracking {
  constructor(
    public id?: number,
    public codeResponse?: string,
    public endPoint?: string,
    public loginActeur?: string,
    public requestId?: string,
    public dateRequest?: Moment,
    public dateResponse?: Moment,
    public requestTr?: any,
    public responseTr?: any
  ) {}
}
