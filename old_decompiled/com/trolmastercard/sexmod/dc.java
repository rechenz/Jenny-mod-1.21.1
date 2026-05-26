/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 *  net.minecraftforge.fml.relauncher.Side
 */
package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.ConcurrentModificationException;
import java.util.UUID;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class dc
implements IMessage {
    boolean c;
    UUID a;
    boolean b;
    boolean d;
    UUID e = null;

    public dc() {
        this.c = false;
    }

    public dc(UUID uUID, UUID uUID2, boolean bl2, boolean bl3) {
        this.a = uUID;
        this.b = bl2;
        this.e = uUID2;
        this.d = bl3;
        this.c = true;
    }

    public void fromBytes(ByteBuf byteBuf) {
        UUID uUID;
        this.a = UUID.fromString(ByteBufUtils.readUTF8String((ByteBuf)byteBuf));
        this.b = byteBuf.readBoolean();
        this.d = byteBuf.readBoolean();
        String string = ByteBufUtils.readUTF8String((ByteBuf)byteBuf);
        try {
            dc dc2 = this;
            uUID = string.equals("null") ? null : UUID.fromString(string);
        }
        catch (RuntimeException runtimeException) {
            throw dc.a(runtimeException);
        }
        dc2.e = uUID;
        this.c = true;
    }

    public void toBytes(ByteBuf byteBuf) {
        String string;
        ByteBuf byteBuf2;
        try {
            ByteBufUtils.writeUTF8String((ByteBuf)byteBuf, (String)this.a.toString());
            byteBuf.writeBoolean(this.b);
            byteBuf.writeBoolean(this.d);
            byteBuf2 = byteBuf;
            string = this.e == null ? "null" : this.e.toString();
        }
        catch (RuntimeException runtimeException) {
            throw dc.a(runtimeException);
        }
        ByteBufUtils.writeUTF8String((ByteBuf)byteBuf2, (String)string);
    }

    private static RuntimeException a(RuntimeException runtimeException) {
        return runtimeException;
    }

    public static class a
    implements IMessageHandler<dc, IMessage> {
        /*
         * Exception decompiling
         */
        public static void a(UUID var0, UUID var1_1, boolean var2_2, boolean var3_3) {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 17[WHILELOOP]
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        }

        public IMessage a(dc dc2, MessageContext messageContext) {
            block4: {
                try {
                    try {
                        if (!dc2.c || messageContext.side != Side.SERVER) break block4;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw com.trolmastercard.sexmod.dc$a.a(concurrentModificationException);
                    }
                    FMLCommonHandler.instance().getMinecraftServerInstance().func_152344_a(() -> com.trolmastercard.sexmod.dc$a.a(dc2.a, dc2.e, dc2.b, dc2.d));
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw com.trolmastercard.sexmod.dc$a.a(concurrentModificationException);
                }
            }
            return null;
        }

        private static RuntimeException a(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

