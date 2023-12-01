import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
    name: 'telefone'
})
export class TelefonePipe implements PipeTransform {

    transform(value: string, ...args: any[]): any {
        let telefoneFormatado: string = value;
        if (value.length > 10) {
            telefoneFormatado = value.replace(/(\d{2})?(\d{5})?(\d{4})/, '($1) $2-$3');

        } else if (value.length > 9) {
            telefoneFormatado = value.replace(/(\d{2})?(\d{4})?(\d{4})/, '($1) $2-$3');
        }
        return telefoneFormatado;
    }

}
