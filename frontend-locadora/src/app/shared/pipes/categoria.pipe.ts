import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'categoria',
})
export class CategoriaPipe implements PipeTransform {
  transform(value: string): string {
    switch (value) {
      case 'Mistério':
        return 'psychology';
      case 'Romance':
        return 'favorite';
      case 'Comédia':
        return 'mood';
      case 'Ação':
        return 'directions_run';
    }
    return 'remove';
  }
}
