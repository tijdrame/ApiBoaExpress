export interface IRequestParam {
  id?: number;
  channelId?: string;
  userId?: string;
  transactionSecret?: string;
  requestId?: string;
  codePartenaire?: string;
  pays?: string;
}

export class RequestParam implements IRequestParam {
  constructor(
    public id?: number,
    public channelId?: string,
    public userId?: string,
    public transactionSecret?: string,
    public requestId?: string,
    public codePartenaire?: string,
    public pays?: string
  ) {}
}
