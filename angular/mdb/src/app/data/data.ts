export class MdbError extends Error {

    text?: string;
    code? : number;
    constructor(parameters:string, code:number) {
      super(parameters)
      this.text = parameters
      this.code = code
    }
  }
  