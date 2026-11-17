# Bonus функцийн DU шинжилгээ

## 1. Шинжилгээний объект

- Файл: `src/main/java/mn/edu/must/sqat/DataFlowTargets.java`
- Функц: `bonus(int[] attendance, int threshold)`
- Мөрүүд: 31–56
- Арга: Definition–Use (DU) шинжилгээ

## 2. Definition ба Use

| Хувьсагч | Definition | Use |
|---|---|---|
| attendance | 31 | 32, 38 |
| threshold | 31 | 32, 50 |
| a | 38 | 39 |
| present | 35, 40 | 40, 50 |
| streak | 36, 41, 46 | 41, 42 |
| best | 37, 43 | 42, 53 |
| score | 49, 51, 54 | 54, 56 |

## 3. DU хосууд ба тестүүд

| Хувьсагч | DU хос | Тест |
|---|---|---|
| attendance | 31 → 32 | testNullAttendance |
| attendance | 31 → 38 | testEmptyAttendance |
| threshold | 31 → 32 | testNegativeThreshold |
| threshold | 31 → 50 | testExactThreshold |
| a | 38 → 39 | testLongStreakWithBreak |
| present | 35 → 40 | testLongStreakWithBreak |
| present | 35 → 50 | testEmptyAttendance |
| present | 40 → 40 | testLongStreakWithBreak |
| present | 40 → 50 | testExactThreshold |
| streak | 36 → 41 | testLongStreakWithBreak |
| streak | 41 → 41 | testLongStreakWithBreak |
| streak | 41 → 42 | testLongStreakWithBreak |
| streak | 41 → 43 | testLongStreakWithBreak |
| streak | 46 → 41 | testShorterStreakAfterBreak |
| best | 37 → 42 | testLongStreakWithBreak |
| best | 37 → 53 | testEmptyAttendance |
| best | 43 → 42 | testLongStreakWithBreak |
| best | 43 → 53 | testLongStreakWithBreak |
| score | 49 → 54 | testLongStreakBelowThreshold |
| score | 49 → 56 | testEmptyAttendance |
| score | 51 → 54 | testLongStreakWithBreak |
| score | 51 → 56 | testExactThreshold |
| score | 54 → 56 | testLongStreakWithBreak |

**Тайлбар:** 41 → 43 хос нь `best = streak` мөрөнд `streak`-ийн утгыг ашиглаж байгааг илэрхийлнэ.

`streak`-ийн 46 → 41 хосыг давхар бичих шаардлагагүй. Нэг DU хосыг олон тест хамарч болно.

Энэ хүснэгт нь эх кодын мөрийн дугаарт тулгуурласан DU хосуудын жагсаалт юм.
## 4. Def-clear замын тайлбар

Def-clear зам гэдэг нь хувьсагчийн definition болон use-ийн хооронд тухайн хувьсагч дахин тодорхойлогдоогүй зам юм.

- `present`: 35-р мөрөөс 50-р мөр хүртэлх замд 40-р мөр ажиллаагүй бол анхны definition хадгалагдана.
- `streak`: 46-р мөр дээр тэглэгдсэн утга дараагийн ирсэн өдөр 41-р мөрийн баруун талд ашиглагдаж болно.
- `best`: 43-р мөр шинэ утга оноох үед өмнөх definition дарагдана.
- `score`: 51 эсвэл 54-р мөр ажиллахад өмнөх `score` definition дарагдана.

## 5. Тестийн үр дүн

- All-defs үе: 5 тест амжилттай
- Нэмэлт тестийн дараа: 11 тест амжилттай
- Branch coverage: 87.50% → 100%
- Line coverage: 91.67% → 95.83%

JaCoCo нь branch болон line coverage хэмждэг. DU-pair coverage-ийг тусдаа өгөгдлийн урсгалын шинжилгээгээр үнэлнэ.

## 6. CFG диаграмм

CFG файлууд:

- `cfg-bonus.dot`
- `cfg-bonus.png`

Диаграммд нөхцөл, давталт, салаалал болон тэдгээрийг гүйцэтгэсэн тестийн нэрсийг тэмдэглэсэн.