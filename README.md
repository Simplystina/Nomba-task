# FX Trade Matching – Java 24

## Task

Build a method that takes in a list of FX trades and returns:

- ✅ **Total Spread Revenue**
- ✅ **Outstanding (unmatched) trades**

Trades consist of BUY and SELL actions with corresponding amounts and rates. The method should use **FIFO matching** logic to compute spread revenue and track any remaining unmatched trades.

## How to Run

```
bash
javac Main.java
java Main
```

## Example Output

```
Total Spread Revenue: 1200.00
Unmatched Trades:
BUY 30000 @ 1.100
```
