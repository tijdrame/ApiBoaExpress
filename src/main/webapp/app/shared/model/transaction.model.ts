import { Moment } from 'moment';

export interface ITransaction {
  id?: number;
  montant?: number;
  montantFrais?: number;
  paysEnvoi?: string;
  paysDestination?: string;
  typeTransaction?: string;
  questionSecrete?: string;
  reponseSecrete?: string;
  referenceBancaire?: string;
  raisonTransfert?: string;
  canal?: string;
  senderName?: string;
  senderPrenom?: string;
  senderTelephone?: string;
  senderCompte?: string;
  beneficiaryName?: string;
  beneficiaryPrenom?: string;
  beneficiaryPhone?: string;
  numeroTransaction?: string;
  isConfirmed?: boolean;
  codeConfim?: string;
  dateCreated?: Moment;
  dateConfirmed?: Moment;
}

export interface IMontantPeriod {
  amount?: number;
  fees?: number;
  country?: string;
  month?: string;
  year?: number;
}

export class MontantPeriod implements IMontantPeriod {
  constructor(
    public amount?: number,
    public fees?: number,
    public month?: string,
    public year?: number,
    public country?: string)
    {}
}

export interface IMontantCountry {
  amount?: number;
  fees?: number;
  country?: string;
}

export class MontantCountry implements IMontantCountry {
  constructor(
    public amount?: number,
    public fees?: number,
    public country?: string)
    {}
}

export interface IMontantTransac {
  montantFrais?: number;
  montant?: number;
}

export class MontantTransac implements IMontantTransac {
  constructor(
    public montantFrais?: number,
    public montant?: number)
    {}
}

export interface ITotalTransac {
  totalConfirme?: number;
  totalGeneral?: number;
}

export class TotalTransac implements ITotalTransac {
  constructor(
    public totalConfirme?: number,
    public totalGeneral?: number)
    {}
}

export class Transaction implements ITransaction {
  constructor(
    public id?: number,
    public montant?: number,
    public paysEnvoi?: string,
    public paysDestination?: string,
    public typeTransaction?: string,
    public questionSecrete?: string,
    public reponseSecrete?: string,
    public referenceBancaire?: string,
    public raisonTransfert?: string,
    public canal?: string,
    public senderName?: string,
    public senderPrenom?: string,
    public senderTelephone?: string,
    public senderCompte?: string,
    public beneficiaryName?: string,
    public beneficiaryPrenom?: string,
    public beneficiaryPhone?: string,
    public numeroTransaction?: string,
    public isConfirmed?: boolean,
    public codeConfim?: string,
    public dateCreated?: Moment,
    public dateConfirmed?: Moment, 
    public montantFrais?: number
  ) {
    this.isConfirmed = this.isConfirmed || false;
  }
}
