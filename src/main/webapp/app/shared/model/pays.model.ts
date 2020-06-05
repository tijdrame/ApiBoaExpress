export interface IPays {
  id?: number;
  pays?: string;
  isoAlpha2?: string;
  isoAlpha3?: string;
  isoNumerique?: string;
}

export class Pays implements IPays {
  constructor(
    public id?: number,
    public pays?: string,
    public isoAlpha2?: string,
    public isoAlpha3?: string,
    public isoNumerique?: string
  ) {}
}
