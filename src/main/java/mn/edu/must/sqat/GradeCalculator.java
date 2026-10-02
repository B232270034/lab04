package mn.edu.must.sqat;

public class GradeCalculator {

    /**
     * Оюутны авсан нийлбэр онооноос үсгэн дүн тооцно.
     * 90+ -> A, 80-89.99 -> B, 70-79.99 -> C, 60-69.99 -> D, <60 -> F
     */
    public String letterGrade(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Оноо 0-ээс 100-ийн хооронд байх ёстой.");
        }
        
        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    /**
     * Бүрэлдэхүүн хэсгүүдийн онооны нийлбэрийг тооцно.
     * Ирц(10), Лаборатори/Бие даалт(40), Сорил 1(10), Сорил 2(10), Шалгалт(30)
     */
    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        if (att < 0 || att > 10) {
            throw new IllegalArgumentException("Ирцийн оноо 0-10 хооронд байх ёстой.");
        }
        if (lab < 0 || lab > 40) {
            throw new IllegalArgumentException("Лабораторийн оноо 0-40 хооронд байх ёстой.");
        }
        if (quiz1 < 0 || quiz1 > 10) {
            throw new IllegalArgumentException("Сорил 1-ийн оноо 0-10 хооронд байх ёстой.");
        }
        if (quiz2 < 0 || quiz2 > 10) {
            throw new IllegalArgumentException("Сорил 2-ын оноо 0-10 хооронд байх ёстой.");
        }
        if (exam < 0 || exam > 30) {
            throw new IllegalArgumentException("Шалгалтын оноо 0-30 хооронд байх ёстой.");
        }

        return att + lab + quiz1 + quiz2 + exam;
    }
}