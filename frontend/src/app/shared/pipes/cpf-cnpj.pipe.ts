import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'cpfCnpj'
})
export class CpfCnpjPipe implements PipeTransform {

  transform(valor: string): string {
    const numeros = this.sanitize(valor);

    if (numeros && numeros.length === 11) {
      return this.formatarCpf(numeros);
    } else if (numeros && numeros.length === 14) {
      return this.formatarCnpj(numeros);
    }
    return '';
  }

  private sanitize(valor: string): string {
    return valor.replace(/\D/g, '');
  }

  formatarCpf(numeros: string): string {
    return `${numeros.substr(0,3)}.${numeros.substr(3,3)}.${numeros.substr(6,3)}-${numeros.substr(9,2)}`;
  }

  formatarCnpj(numeros: string): string {
    return `${numeros.substr(0,2)}.${numeros.substr(2,3)}.${numeros.substr(5,3)}/${numeros.substr(8,4)}-${numeros.substr(12,2)}`;
  }
}
