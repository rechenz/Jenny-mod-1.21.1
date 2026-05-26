/*
 * Decompiled with CFR 0.152.
 */
package software.bernie.shadowed.fasterxml.jackson.core.format;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import software.bernie.shadowed.fasterxml.jackson.core.JsonFactory;
import software.bernie.shadowed.fasterxml.jackson.core.format.DataFormatMatcher;
import software.bernie.shadowed.fasterxml.jackson.core.format.InputAccessor;
import software.bernie.shadowed.fasterxml.jackson.core.format.MatchStrength;

public class DataFormatDetector {
    public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;
    protected final JsonFactory[] _detectors;
    protected final MatchStrength _optimalMatch;
    protected final MatchStrength _minimalMatch;
    protected final int _maxInputLookahead;

    public DataFormatDetector(JsonFactory ... detectors) {
        this(detectors, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
    }

    public DataFormatDetector(Collection<JsonFactory> detectors) {
        this(detectors.toArray(new JsonFactory[detectors.size()]));
    }

    public DataFormatDetector withOptimalMatch(MatchStrength optMatch) {
        if (optMatch == this._optimalMatch) {
            return this;
        }
        return new DataFormatDetector(this._detectors, optMatch, this._minimalMatch, this._maxInputLookahead);
    }

    public DataFormatDetector withMinimalMatch(MatchStrength minMatch) {
        if (minMatch == this._minimalMatch) {
            return this;
        }
        return new DataFormatDetector(this._detectors, this._optimalMatch, minMatch, this._maxInputLookahead);
    }

    public DataFormatDetector withMaxInputLookahead(int lookaheadBytes) {
        if (lookaheadBytes == this._maxInputLookahead) {
            return this;
        }
        return new DataFormatDetector(this._detectors, this._optimalMatch, this._minimalMatch, lookaheadBytes);
    }

    private DataFormatDetector(JsonFactory[] detectors, MatchStrength optMatch, MatchStrength minMatch, int maxInputLookahead) {
        this._detectors = detectors;
        this._optimalMatch = optMatch;
        this._minimalMatch = minMatch;
        this._maxInputLookahead = maxInputLookahead;
    }

    public DataFormatMatcher findFormat(InputStream in) throws IOException {
        return this._findFormat(new InputAccessor.Std(in, new byte[this._maxInputLookahead]));
    }

    public DataFormatMatcher findFormat(byte[] fullInputData) throws IOException {
        return this._findFormat(new InputAccessor.Std(fullInputData));
    }

    public DataFormatMatcher findFormat(byte[] fullInputData, int offset, int len) throws IOException {
        return this._findFormat(new InputAccessor.Std(fullInputData, offset, len));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int len = this._detectors.length;
        if (len > 0) {
            sb.append(this._detectors[0].getFormatName());
            for (int i2 = 1; i2 < len; ++i2) {
                sb.append(", ");
                sb.append(this._detectors[i2].getFormatName());
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private DataFormatMatcher _findFormat(InputAccessor.Std acc) throws IOException {
        JsonFactory bestMatch = null;
        Enum bestMatchStrength = null;
        for (JsonFactory f10 : this._detectors) {
            acc.reset();
            MatchStrength strength = f10.hasFormat(acc);
            if (strength == null || strength.ordinal() < this._minimalMatch.ordinal() || bestMatch != null && bestMatchStrength.ordinal() >= strength.ordinal()) continue;
            bestMatch = f10;
            bestMatchStrength = strength;
            if (strength.ordinal() >= this._optimalMatch.ordinal()) break;
        }
        return acc.createMatcher(bestMatch, (MatchStrength)bestMatchStrength);
    }
}

