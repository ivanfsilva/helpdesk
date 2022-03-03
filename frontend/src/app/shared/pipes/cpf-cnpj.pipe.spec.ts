import { CpfCnpjPipe } from './cpf-cnpj.pipe';

describe(`${CpfCnpjPipe.name}`, () => {
  it(`formatarCpf should format CPF number when receiving an unformatted number `, () => {
    const pipe = new CpfCnpjPipe();
    const cpfFormatado = pipe.formatarCpf('76657187521');
    expect(cpfFormatado).toContain('766.571.875-21');
  });
});
