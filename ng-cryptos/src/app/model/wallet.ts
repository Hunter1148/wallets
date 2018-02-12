import {PricingService} from "../pricing.service";
import {isUndefined} from "util";
import {User} from "./user";

export class Line {
  constructor(public symbol: string, public quantity: number) {

  }
}

export class Wallet {
  user? :User;
  lines: Line [] = [];
  name:String;
  pricingService: PricingService;

  deposit(dollars: number) {
    let usdLine = this.lines.find(line => line.symbol === "USD");
    if (usdLine === undefined) {
      this.lines.push(new Line('USD', dollars));
    } else {
      usdLine.quantity += dollars;
    }


  }

  buy(quantity: number, symbol: string) {
    let usdline = this.lines.find(line => line.symbol === 'USD');
    let symbolPrice = this.pricingService.priceToDollar(quantity, symbol);
    usdline.quantity = usdline.quantity - symbolPrice;

    let symbolLine = this.lines.find(line => line.symbol === symbol);
    if (symbolLine === undefined) {
      this.lines.push(new Line(symbol, quantity))
    } else {
      symbolLine.quantity += quantity;
    }
  }

  sell(quantity: number, symbol: string ) {

    let dollarLine = this.lines.find(line => line.symbol === 'USD');
    let symbolPrice = this.pricingService.priceToDollar(quantity, symbol);
     dollarLine.quantity += symbolPrice;

    let symbolLine1 = this.lines.find(line => line.symbol === symbol)
    if (symbolLine1.quantity >= quantity) {
      symbolLine1.quantity -= quantity
    } else {
      symbolLine1.quantity;
    }
  }

  allToDollar(): number {
    let total = 0;
    for (let i = 0; i < this.lines.length; i++) {
      let line = this.lines[i];
      if (line.symbol === 'USD') {
        total = total + line.quantity
      } else {

        total = total + this.pricingService.priceToDollar(line.quantity, line.symbol)


      }
      return total;

    }
  }

}










