package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    /**
     * Creates a new {@code MonthSorterNested}.
     */
    public MonthSorterNested() {
        // Nothing to initialize
    }

    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<>() {
            @Override
            public int compare(final String o1, final String o2) {
                return Integer.compare(Month.fromString(o1).getDays(), Month.fromString(o2).getDays());
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<>() {
            @Override
            public int compare(final String o1, final String o2) {
                return Month.fromString(o1).compareTo(Month.fromString(o2));
            }
        };
    }

    private enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int days;

        Month(final int days) {
            this.days = days;
        }

        int getDays() {
            return days;
        }

        static Month fromString(final String name) {
            final String upper = Objects.requireNonNull(name, "The name cannot be null")
                .toUpperCase(Locale.ROOT);
            Month result = null;
            for (final Month m : values()) {
                if (m.name().startsWith(upper)) {
                    if (result != null) {
                        throw new IllegalArgumentException("Ambiguous month name: " + name);
                    }
                    result = m;
                }
            }
            if (result == null) {
                throw new IllegalArgumentException("No month matches: " + name);
            }
            return result;
        }
    }
}
