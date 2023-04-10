import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'replace' })
export class ReplacePipe implements PipeTransform {
  transform(value: string, find: string, replaceWith: string): string {
    return value.replace(new RegExp(find, 'g'), replaceWith);
  }
}