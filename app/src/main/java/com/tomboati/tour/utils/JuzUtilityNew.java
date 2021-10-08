package com.tomboati.tour.utils;

import com.tomboati.tour.model.JuzModelNew;

import java.util.ArrayList;
import java.util.List;

public class JuzUtilityNew {

    private final List<JuzModelNew> listJuz = new ArrayList<>();

    public JuzUtilityNew() {

        /* ==== Juz 1  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Fatihah Ayat 1",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(1),
                        new JuzModelNew.ListSurah(2, 1, 141)
                }
        ));

        /* ==== Juz 2  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Baqarah Ayat 142",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(2, 142, 252)
                }
        ));

        /* ==== Juz 3  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Baqarah Ayat 253",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(2, 253, 286),
                        new JuzModelNew.ListSurah(3, 1, 91)
                }
        ));

        /* ==== Juz 4  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Ali - Imron Ayat 92",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(3, 92, 200),
                        new JuzModelNew.ListSurah(4, 1, 23)
                }
        ));

        /* ==== Juz 5  ==== */
        listJuz.add(new JuzModelNew("Mulai di : An - Nisa Ayat 24",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(4, 24, 147),
                }
        ));

        /* ==== Juz 6  ==== */
        listJuz.add(new JuzModelNew("Mulai di : An - Nisa Ayat 148",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(4, 148, 176),
                        new JuzModelNew.ListSurah(5, 1, 82)
                }
        ));

        /* ==== Juz 7  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Ma'idah Ayat 83",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(5, 83, 120),
                        new JuzModelNew.ListSurah(6, 1, 110)
                }
        ));

        /* ==== Juz 8  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - An'am Ayat 111",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(6, 111, 165),
                        new JuzModelNew.ListSurah(7, 1, 87)
                }
        ));

        /* ==== Juz 9  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - A'raf Ayat 88",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(7, 88, 206),
                        new JuzModelNew.ListSurah(8, 1, 40)
                }
        ));

        /* ==== Juz 10  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Anfal Ayat 41",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(8, 41, 75),
                        new JuzModelNew.ListSurah(9, 1, 93)
                }
        ));

        /* ==== Juz 11  ==== */
        listJuz.add(new JuzModelNew("Mulai di : At - Taubah Ayat 94",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(9, 94, 129),
                        new JuzModelNew.ListSurah(10),
                        new JuzModelNew.ListSurah(11, 1, 5)
                }
        ));

        /* ==== Juz 12  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Hud Ayat 6",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(11, 6, 123),
                        new JuzModelNew.ListSurah(12, 1, 52)
                }
        ));

        /* ==== Juz 13  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Yusuf Ayat 53",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(12, 53, 111),
                        new JuzModelNew.ListSurah(13),
                        new JuzModelNew.ListSurah(14),
                        new JuzModelNew.ListSurah(15, 1, 1),
                }
        ));

        /* ==== Juz 14  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Hijr Ayat 2",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(15, 2, 99),
                        new JuzModelNew.ListSurah(16)
                }
        ));

        /* ==== Juz 15  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Isra' Ayat 1",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(17),
                        new JuzModelNew.ListSurah(18, 1, 74)
                }
        ));

        /* ==== Juz 16  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Kahfi Ayat 75",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(18, 75, 110),
                        new JuzModelNew.ListSurah(19),
                        new JuzModelNew.ListSurah(20),
                }
        ));

        /* ==== Juz 17  ==== */
        listJuz.add(new JuzModelNew("Mulai di : An - Anbiya Ayat 1",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(21),
                        new JuzModelNew.ListSurah(22),
                }
        ));

        /* ==== Juz 18  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Mu'minun Ayat 1",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(23),
                        new JuzModelNew.ListSurah(24),
                        new JuzModelNew.ListSurah(25, 1, 20),
                }
        ));

        /* ==== Juz 19  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Furqon Ayat 21",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(25, 21, 77),
                        new JuzModelNew.ListSurah(26),
                        new JuzModelNew.ListSurah(27, 1, 59),
                }
        ));

        /* ==== Juz 20  ==== */
        listJuz.add(new JuzModelNew("Mulai di : An - Naml Ayat 60",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(27, 60, 93),
                        new JuzModelNew.ListSurah(28),
                        new JuzModelNew.ListSurah(29, 1, 44),
                }
        ));

        /* ==== Juz 21  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - 'Ankabut Ayat 45",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(29, 45, 69),
                        new JuzModelNew.ListSurah(30),
                        new JuzModelNew.ListSurah(31),
                        new JuzModelNew.ListSurah(32),
                        new JuzModelNew.ListSurah(33, 1, 30),
                }
        ));

        /* ==== Juz 22  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Ahzab Ayat 31",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(33, 31, 73),
                        new JuzModelNew.ListSurah(34),
                        new JuzModelNew.ListSurah(35),
                        new JuzModelNew.ListSurah(36, 1, 21),
                }
        ));

        /* ==== Juz 23  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Ya Sin Ayat 22",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(36, 22, 83),
                        new JuzModelNew.ListSurah(37),
                        new JuzModelNew.ListSurah(38),
                        new JuzModelNew.ListSurah(39, 1, 31),
                }
        ));

        /* ==== Juz 24  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Az - Zumar Ayat 32",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(39, 32, 75),
                        new JuzModelNew.ListSurah(40),
                        new JuzModelNew.ListSurah(41, 1, 46),
                }
        ));

        /* ==== Juz 25  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Fussilat Ayat 47",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(41, 47, 54),
                        new JuzModelNew.ListSurah(42),
                        new JuzModelNew.ListSurah(43),
                        new JuzModelNew.ListSurah(44),
                        new JuzModelNew.ListSurah(45),
                }
        ));

        /* ==== Juz 26  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Ahqaf Ayat 1",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(46),
                        new JuzModelNew.ListSurah(47),
                        new JuzModelNew.ListSurah(48),
                        new JuzModelNew.ListSurah(49),
                        new JuzModelNew.ListSurah(50),
                        new JuzModelNew.ListSurah(51, 1, 30),
                }
        ));

        /* ==== Juz 27  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Az - Zariyat Ayat 31",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(51, 31, 60),
                        new JuzModelNew.ListSurah(52),
                        new JuzModelNew.ListSurah(53),
                        new JuzModelNew.ListSurah(54),
                        new JuzModelNew.ListSurah(55),
                        new JuzModelNew.ListSurah(56),
                        new JuzModelNew.ListSurah(57),
                }
        ));

        /* ==== Juz 28  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Mujadilah Ayat 1",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(58),
                        new JuzModelNew.ListSurah(59),
                        new JuzModelNew.ListSurah(60),
                        new JuzModelNew.ListSurah(61),
                        new JuzModelNew.ListSurah(62),
                        new JuzModelNew.ListSurah(63),
                        new JuzModelNew.ListSurah(64),
                        new JuzModelNew.ListSurah(65),
                        new JuzModelNew.ListSurah(66),
                }
        ));

        /* ==== Juz 29  ==== */
        listJuz.add(new JuzModelNew("Mulai di : Al - Mulk Ayat 1",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(67),
                        new JuzModelNew.ListSurah(68),
                        new JuzModelNew.ListSurah(69),
                        new JuzModelNew.ListSurah(70),
                        new JuzModelNew.ListSurah(71),
                        new JuzModelNew.ListSurah(72),
                        new JuzModelNew.ListSurah(73),
                        new JuzModelNew.ListSurah(74),
                        new JuzModelNew.ListSurah(75),
                        new JuzModelNew.ListSurah(76),
                        new JuzModelNew.ListSurah(77)
                }
        ));

        /* ==== Juz 30  ==== */
        listJuz.add(new JuzModelNew("Mulai di : An - Naba' Ayat 1",
                new JuzModelNew.ListSurah[]{
                        new JuzModelNew.ListSurah(78),
                        new JuzModelNew.ListSurah(79),
                        new JuzModelNew.ListSurah(80),
                        new JuzModelNew.ListSurah(81),
                        new JuzModelNew.ListSurah(82),
                        new JuzModelNew.ListSurah(83),
                        new JuzModelNew.ListSurah(84),
                        new JuzModelNew.ListSurah(85),
                        new JuzModelNew.ListSurah(86),
                        new JuzModelNew.ListSurah(87),
                        new JuzModelNew.ListSurah(88),
                        new JuzModelNew.ListSurah(89),
                        new JuzModelNew.ListSurah(90),
                        new JuzModelNew.ListSurah(91),
                        new JuzModelNew.ListSurah(92),
                        new JuzModelNew.ListSurah(93),
                        new JuzModelNew.ListSurah(94),
                        new JuzModelNew.ListSurah(95),
                        new JuzModelNew.ListSurah(96),
                        new JuzModelNew.ListSurah(97),
                        new JuzModelNew.ListSurah(98),
                        new JuzModelNew.ListSurah(99),
                        new JuzModelNew.ListSurah(100),
                        new JuzModelNew.ListSurah(101),
                        new JuzModelNew.ListSurah(102),
                        new JuzModelNew.ListSurah(103),
                        new JuzModelNew.ListSurah(104),
                        new JuzModelNew.ListSurah(105),
                        new JuzModelNew.ListSurah(106),
                        new JuzModelNew.ListSurah(107),
                        new JuzModelNew.ListSurah(108),
                        new JuzModelNew.ListSurah(109),
                        new JuzModelNew.ListSurah(110),
                        new JuzModelNew.ListSurah(111),
                        new JuzModelNew.ListSurah(112),
                        new JuzModelNew.ListSurah(113),
                        new JuzModelNew.ListSurah(114),
                }
        ));

    }

    public List<JuzModelNew> getListJuz() {
        return this.listJuz;
    }
}
