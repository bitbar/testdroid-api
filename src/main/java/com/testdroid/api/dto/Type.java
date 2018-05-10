package com.testdroid.api.dto;

/**
 * Enum represents type of argument to parsing
 * <ul>
 * <li><code>N</code> stands for <code>Number</code> type
 * <li><code>D</code> stands for <code>Date</code> type
 * <li><code>S</code> stands for <code>String</code> type
 * <li><code>B</code> stands for <code>Boolean</code> type
 * <li><code>LN<code/> stands for <code>List<Number></code> type
 * <li><code>LD<code/> stands for <code>List<Date></code> type
 * <li><code>LS<code/> stands for <code>List<String></code> type
 * <li><code>LB<code/> stands for <code>List<Boolean></code> type
 * </ul>
 *
 * @author Damian Sniezek <damian.sniezek@bitbar.com>
 *
 */
public enum Type {
    N,
    D,
    S,
    B,
    LN,
    LD,
    LS,
    LB
}
