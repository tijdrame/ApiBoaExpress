export interface IParamEndPoint {
  id?: number;
  codeParam?: string;
  endPoints?: string;
  attribute01?: string;
  attribute02?: string;
  attribute03?: string;
  attribute04?: string;
}

export class ParamEndPoint implements IParamEndPoint {
  constructor(
    public id?: number,
    public codeParam?: string,
    public endPoints?: string,
    public attribute01?: string,
    public attribute02?: string,
    public attribute03?: string,
    public attribute04?: string
  ) {}
}
