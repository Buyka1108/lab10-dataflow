
# Лаборатори №10 — Өгөгдлийн урсгалын тест (Definition-Use хосууд)

**Оюутны нэр:** Ж.Буянхишиг  
**Оюутны код:** B232270114  
**Төсөл:** lab10-dataflow  
**GitHub:** https://github.com/Buyka1108/lab10-dataflow  
**Ажиллах branch:** lab10-work

## 1. Лабораторийн зорилго

Энэхүү лабораторийн ажлын зорилго нь өгөгдлийн урсгалын тестийн Definition-Use (DU) хосын ойлголтыг судалж, Java функцийн хувьсагчдын тодорхойлолт (definition) болон ашиглалт (use)-ыг шинжлэх, JUnit 5 ашиглан тест бичиж ажиллуулах, JaCoCo ашиглан кодын хамралтыг шалгахад оршино.

## 2. Ашигласан технологи

- Ubuntu WSL2
- Visual Studio Code
- Java OpenJDK 17
- Apache Maven 3.9.12
- JUnit 5
- JaCoCo 0.8.12
- Git болон GitHub

### 2.1 Java хувилбар

`java -version` командын үр дүн:

```text
openjdk version "17.0.20.1" 2026-08-18
OpenJDK Runtime Environment (build 17.0.20.1+1-1-26.04-Ubuntu)
OpenJDK 64-Bit Server VM (build 17.0.20.1+1-1-26.04-Ubuntu, mixed mode, sharing)
```

### 2.2 Maven хувилбар

`mvn -version` командын үр дүн:

```text
Apache Maven 3.9.12
Maven home: /usr/share/maven
Java version: 17.0.20.1
OS name: "linux"
OS version: "6.6.114.1-microsoft-standard-wsl2"
```

## 3. Төслийн орчин бэлтгэсэн байдал

Starter төслийг Windows-ийн D дискнээс Ubuntu WSL2 орчин руу хуулсан.

Эх файлын байршил:

```text
D:\user\Downloads\starter\lab10-dataflow
```

Ubuntu дахь төслийн байршил:

```text
/home/bbuyk/lab10-dataflow
```

Төслийг VS Code дээр нээсэн.

### 3.1 Төслийн бүтэц

```text
lab10-dataflow/
├── .gitignore
├── pom.xml
└── src/
    ├── main/
    │   └── java/mn/edu/must/sqat/
    │       └── DataFlowTargets.java
    └── test/
        └── java/mn/edu/must/sqat/
            └── GcdTest.java
```

## 4. Git болон GitHub тохиргоо

Төслийг Git repository болгож, эхний Starter commit-ийг хийсэн.

```bash
git init
git add -A
git commit -m "Starter"
```

Starter commit:

```text
1623ea0 Starter
```

Үндсэн branch-ийг `main` гэж нэрлэж, лабораторийн ажлыг хийх `lab10-work` branch үүсгэсэн.

```bash
git branch -M main
git switch -c lab10-work
```

GitHub дээр `lab10-dataflow` нэртэй Public repository үүсгэж, локал төсөлтэй холбосон.

```bash
git remote add origin https://github.com/Buyka1108/lab10-dataflow.git
git push -u origin main
git push -u origin lab10-work
```

Хоёр branch GitHub руу амжилттай push хийгдсэн.

### 4.1 Хийгдсэн commit-ууд

| Commit ID | Commit message |
|---|---|
| 1623ea0 | Starter |
| cd1c685 | Add gcd DU-pair tests |
| 1b1c96f | Implement gcd JUnit tests |

## 5. Хэсэг A — gcd() функцийн өгөгдлийн урсгалын тест

### 5.1 gcd() функц

`DataFlowTargets.java` файлд байгаа `gcd()` функц нь Евклидийн алгоритм ашиглан хоёр бүхэл тооны хамгийн их ерөнхий хуваагчийг олдог.

```java
public static int gcd(int x, int y) {
    int tmp;
    while (y != 0) {
        tmp = x % y;
        x = y;
        y = tmp;
    }
    return x;
}
```

### 5.2 Definition ба Use шинжилгээ

Функцэд `x`, `y`, `tmp` гэсэн гурван хувьсагч ашиглагдана.

- **Definition (def):** Хувьсагчид утга оноох үйлдэл.
- **Computational use (c-use):** Хувьсагчийг тооцоолол эсвэл утга буцаахад ашиглах.
- **Predicate use (p-use):** Хувьсагчийг нөхцөл шалгахад ашиглах.
- **Def-clear path:** Тухайн хувьсагчид дахин утга оноохгүйгээр definition цэгээс use цэг хүртэл хүрэх зам.

### 5.3 gcd() функцийн DU хосууд

| Хувьсагч | Def мөр | Use мөр | Төрөл | Тест |
|---|---|---|---|---|
| x | 13 | 16 | c-use | G2 |
| x | 13 | 20 | c-use | G1 |
| x | 17 | 16 | c-use | G2 |
| x | 17 | 20 | c-use | G2 |
| y | 13 | 15 | p-use | G1, G2 |
| y | 13 | 16 | c-use | G2 |
| y | 13 | 17 | c-use | G2 |
| y | 18 | 15 | p-use | G2 |
| y | 18 | 16 | c-use | G2 |
| y | 18 | 17 | c-use | G2 |
| tmp | 16 | 18 | c-use | G2 |

Эдгээр DU хосыг `gcd()` функцийн эх кодын мөрүүд болон давталтын урсгалд үндэслэн тодорхойлсон.

### 5.4 JUnit 5 тестүүд

`src/test/java/mn/edu/must/sqat/GcdTest.java` файлд дараах гурван тестийг хэрэгжүүлсэн.

**G1 — Давталт хийгдэхгүй тохиолдол**

```java
@Test
@DisplayName("G1: zero iterations, x line 13 to line 20")
void testZeroIteration() {
    assertEquals(7, DataFlowTargets.gcd(7, 0));
}
```

**G2 — Олон давталттай тохиолдол**

```java
@Test
@DisplayName("G2: multiple iterations, loop-carried DU pairs")
void testMultipleIterations() {
    assertEquals(4, DataFlowTargets.gcd(12, 8));
}
```

**G3 — Эхний x утга 0 байх тохиолдол**

```java
@Test
@DisplayName("G3: initial x zero")
void testInitialXZero() {
    assertEquals(5, DataFlowTargets.gcd(0, 5));
}
```

### 5.5 Тест ажиллуулсан үр дүн

Дараах командыг ашигласан.

```bash
mvn clean verify
```

Maven-ийн бодит үр дүн:

```text
[INFO] Running mn.edu.must.sqat.GcdTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

[INFO] Results:
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

[INFO] --- jacoco:0.8.12:report (report) @ lab10-dataflow ---
[INFO] Loading execution data file /home/bbuyk/lab10-dataflow/target/jacoco.exec
[INFO] Analyzed bundle 'lab10-dataflow' with 1 classes

[INFO] BUILD SUCCESS
[INFO] Total time: 7.520 s
```

**Үр дүн:** Бичсэн 3 JUnit тест бүгд амжилттай ажилласан. Алдаа болон амжилтгүй тест гараагүй.

### 5.6 JaCoCo

JaCoCo 0.8.12 ашиглан кодын хамралтын тайлан үүсгэсэн.

Тайлангийн байршил:

```text
target/site/jacoco/index.html
target/site/jacoco/jacoco.csv
```

Maven-ийн гаралтаар JaCoCo execution data-г амжилттай уншиж, нэг class-д анализ хийсэн нь батлагдсан.
## Хэсэг B — bonus() функцийн Data Flow Testing

`bonus(int[] attendance, int threshold)` функцийн CFG диаграмм болон Definition–Use шинжилгээг хийв.

### CFG диаграмм

![bonus CFG](cfg-bonus.png)

CFG-ийн эх файл: `cfg-bonus.dot`

### DU шинжилгээ

DU шинжилгээний дэлгэрэнгүй хүснэгт болон def-clear замын тайлбарыг `docs/bonus-du-analysis.md` файлд хадгалсан.

### JUnit тестүүд

`BonusTest.java` файлд нийт 8 тест хэрэгжүүлсэн.

- `testLongStreakWithBreak`
- `testEmptyAttendance`
- `testAllAbsentThresholdZero`
- `testExactThreshold`
- `testLongStreakBelowThreshold`
- `testNullAttendance`
- `testNegativeThreshold`
- `testShorterStreakAfterBreak`

### JaCoCo хамралт

| Үзүүлэлт | All-defs үе | Нэмэлт тестийн дараа |
|---|---:|---:|
| Нийт тест | 5 | 11 |
| Branch coverage | 87.50% | 100% |
| Line coverage | 91.67% | 95.83% |
| Instruction coverage | 90.12% | 96.30% |

Эдгээр нь `DataFlowTargets` классын JaCoCo хамралтын үзүүлэлтүүд юм. JaCoCo нь DU хосын хамралтыг шууд хэмждэггүй.

## Хэсэг C — Fault Seeding

`bonus()` функцэд гурван төрлийн өөрчлөлтийг түр хугацаанд оруулж, JUnit тестүүдээр шалгасан.

| Алдаа | Өөрчлөлт | Үр дүн |
|---|---|---|
| M1 | `streak = streak + 1` мөрийг устгасан | 2 тест FAIL |
| M2 | `best = present` болгосон | 11 тест PASS |
| M3 | Ашиглагдахгүй `total` хувьсагч нэмсэн | 11 тест PASS |

### Дүгнэлт

M1 өөрчлөлтийг тестүүд илрүүлсэн. M2-ийн үед тестүүд амжилттай ажилласан тул уг өөрчлөлтийг илрүүлээгүй. M3 нь програмын буцаах утгад нөлөөлөхгүй, ашиглагдахгүй definition нэмсэн тул JUnit тестүүд амжилттай ажилласан.

Туршилт бүрийн дараа эх кодыг `git restore` командаар сэргээсэн.

## Эцсийн баталгаажуулалт

`mvn clean verify` командыг ажиллуулахад:

- GcdTest: 3 PASS
- BonusTest: 8 PASS
- Нийт: 11 PASS
- Failures: 0
- Errors: 0
- BUILD SUCCESS

Эцсийн JaCoCo үр дүн:

- Branch coverage: 100%
- Line coverage: 95.83%
- Instruction coverage: 96.30%

Үр дүнгийн файлууд `results/` хавтсанд хадгалагдсан.