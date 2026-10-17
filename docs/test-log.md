# Test Log - Exercise 1: Study Budget

| Prueba # | Descripción / Caso de Entrada | Predicción | Resultado Real | Estado |
| :--- | :--- | :--- | :--- | :--- |
| **1** | `" Ana Lee "`, 95 min, Activo: true | 3 bloques, 5 min sobra, sin descuento, Total: $15.00 | 3 bloques, 5 min sobra, sin descuento, Total: $15.00 | Pass |
| **2** | `"Carlos Ruiz"`, 120 min, Activo: true | 4 bloques, aplica 20% desc., Total: $16.00 | 4 bloques, aplica 20% desc., Total: $16.00 | Pass |
| **3** | `"Maria Gomez"`, 120 min, Activo: false | 4 bloques, sin desc. por inactivo, Total: $20.00 | 4 bloques, sin desc. por inactivo, Total: $20.00 | Pass |
| **4** | `"Pedro Perez"`, 29 min, Activo: true | 0 bloques completos, Total: $0.00 | 0 bloques completos, Total: $0.00 | Pass |
| **5** | `"   "`, 60 min, Activo: true | Error de validación por nombre vacío | Muestra mensaje de error y detiene ejecución | Pass |
| **6** | `"Ana Lee"`, 0 min, Activo: true | Error de validación por minutos en cero | Muestra mensaje de error y detiene ejecución | Pass |
| **7** | `"  lucia mendez "`, 150 min, Activo: true | Limpieza de mayúsculas, 5 bloques ($25), Total: $20.00 | Limpieza correcta, Total: $20.00 | Pass |

# Test Log - Exercise 2: Attendance Analyzer

| Prueba # | Caso de Entrada / Acción | Predicción | Resultado Real | Estado |
| :--- | :--- | :--- | :--- | :--- |
| **E2-1** | Menú opción `1` (Matriz estándar) | Gran total: 18, Promedio: 1.50, Sala mayor: Room 2 | Gran total: 18, Promedio: 1.50, Sala mayor: Room 2 | Pass |
| **E2-2** | Menú opción `2`, buscar sala `1` | Muestra 6 visitas y estado de la sala 1 | Muestra 6 visitas y estado de la sala 1 | Pass |
| **E2-3** | Menú opción `2`, buscar sala `3` | Muestra 5 visitas y estado de la sala 3 | Muestra 5 visitas y estado de la sala 3 | Pass |
| **E2-4** | Menú opción `2`, buscar sala `4` | Error de rango superado, vuelve al menú | Error de rango superado, vuelve al menú | Pass |
| **E2-5** | Menú opción `2`, buscar sala `0` | Error de rango menor, vuelve al menú | Error de rango menor, vuelve al menú | Pass |
| **E2-6** | Búsqueda de sala con texto `"dos"` | Captura `NumberFormatException` y avisa error | Captura `NumberFormatException` y avisa error | Pass |
| **E2-7** | Menú principal opción inválida `5` | Mensaje de opción no soportada | Mensaje de opción no soportada | Pass |
| **E2-8** | Menú principal con texto `"abc"` | Maneja la excepción numérica y reintenta | Maneja la excepción numérica y reintenta | Pass |
| **E2-9** | Menú opción `0` (Salir) | Finaliza la ejecución del programa | Finaliza la ejecución del programa | Pass |
| **E2-10**| Resumen con matriz de empate | Aplica regla de desempate al índice menor | Aplica regla de desempate al índice menor | Pass |
| **E2-11**| Resumen con matriz de ceros | Total 0, promedio 0.00, sin salas ocupadas | Total 0, promedio 0.00, sin salas ocupadas | Pass |

# Test Log - Exercise 3: Study Room

| Test ID | Input and Starting State | Predicted Result | Actual Result / Explanation | Status |
| :--- | :--- | :--- | :--- | :--- |
| **E3-T1** | New room, capacity 6; reserve 4 | Success; 4 reserved, 2 remaining | Success; 4 reserved, 2 remaining | Pass |
| **E3-T2** | Room A (capacity 5, currently 4 reserved); try to reserve 4 more | Rejected (exceeds capacity); preserves prior state (4 reserved) | Rejected; state remains unchanged (4 reserved) | Pass |
| **E3-T3** | Room B (default capacity 4); call default `reserve()` (1 seat) | Success; 1 reserved, 3 remaining | Success; 1 reserved, 3 remaining | Pass |
| **E3-T4** | Room A (4 reserved); cancel 2 seats | Success; 2 reserved, 3 remaining | Success; 2 reserved, 3 remaining | Pass |
| **E3-T5** | Room A (2 reserved); try to cancel 10 seats | Rejected (exceeds reserved count); preserves state | Rejected; state remains unchanged (2 reserved) | Pass |
| **E3-T6** | Alias demonstration: `originalRoom` capacity 10; `aliasRoom` points to it and reserves 5 | `originalRoom` reflects 5 reserved and 5 remaining due to shared reference | Both references show 5 reserved and 5 remaining | Pass |
---
# Test Log - Exercise 4: Campus Resource Reservation App

| Test ID / Step | Input and Starting State | Predicted Result | Actual Result / Explanation | Status |
| :--- | :--- | :--- | :--- | :--- |
| **E4-Step A** | Reserve Resource 1, 2 seats | Success; 2 reserved, 2 remaining | Success; 2 reserved, 2 remaining | Pass |
| **E4-Step B** | Reserve Resource 1, 3 seats | Rejected (exceeds capacity); keeps 2 reserved | Rejected; state remains unchanged | Pass |
| **E4-Step C** | Reserve Resource 2, 1 seat | Success; 1 reserved, 2 remaining | Success; 1 reserved, 2 remaining | Pass |
| **E4-Step D** | Cancel Resource 1, 1 seat | Success; 1 reserved, 3 remaining | Success; 1 reserved, 3 remaining | Pass |
| **E4-Step E** | Reserve Resource 3, 2 seats | Success; 2 reserved, 0 remaining | Success; 2 reserved, 0 remaining | Pass |
| **E4-Step F** | Cancel Resource 2, 2 seats | Rejected (exceeds current reservations) | Rejected; state remains unchanged | Pass |
| **E4-Step G** | Reserve Resource 4 (Out of bounds), 1 seat | Application-level rejection (invalid resource number) | Caught out of bounds, returns to menu | Pass |
| **E4-Step H** | Reserve Resource 1, 0 seats | Rejected (non-positive seat count) | Rejected safely | Pass |
| **E4-Step I** | Print Report (Step I) | Display table via StringBuilder; total revenue: $17.00 | Displays correct table; Total revenue equals $17.00 | Pass |
| **E4-Add 1** | Reserve negative seats (`-2`) | Rejected by object-level validation | Rejected; state preserved | Pass |
| **E4-Add 2** | Non-numeric menu input (`"abc"`) | `NumberFormatException` caught, loop continues | Caught safely, returns to menu | Pass |
| **E4-Add 3** | Consecutive Report calls | Second report prints identical state without changes | State remains invariant across reports | Pass |