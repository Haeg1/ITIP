# Лабораторная работа 2: Классы и инкапсуляция

## Инварианты BankAccount

- `balance >= 0` всегда.
- Конструктор запрещает отрицательный начальный баланс.
- `deposit(amount)` принимает только `amount > 0`.
- `withdraw(amount)` принимает только `amount > 0` и `amount <= balance`.
- Сеттера баланса нет.

## Инварианты DataSample

- `id != null`, не пустой.
- `label != null`, не пустой.
- `status != null`.
- `features != null`, `features.length > 0`.
- Массив копируется в конструкторе и в `getFeatures()`.
- `status` меняется только через `changeStatus`, `null` запрещён.
- `isReady() == true` только при `status == READY`.

## Дополнительно

- `SampleId` — неизменяемый record, `value` не может быть `null` или пустым.
- `normalized(min, max)` возвращает новый `DataSample`, исходный не меняется.
