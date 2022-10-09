package org.sopt.sample.week1.common

enum class Mbti {
    INTJ,
    INTP,
    INFJ,
    INFP,
    ISTJ,
    ISTP,
    ISFJ,
    ISFP,
    ENTJ,
    ENTP,
    ENFJ,
    ENFP,
    ESTJ,
    ESTP,
    ESFJ,
    ESFP,
    INVALID;

    companion object {
        fun contains(target: String): Boolean =
            values().any { mbti -> mbti.toString() == target }

        fun from(target: String) = try {
            valueOf(target.uppercase())
        } catch (e: Exception) {
            INVALID
        }
    }
}