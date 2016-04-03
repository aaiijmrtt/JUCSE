module graytobinary(in, out);
	input wire[3: 0] in;
	output wire[3: 0] out;
	assign out[0] = in[0];
	assign out[1] = in[1] ^ in[0];
	assign out[2] = in[2] ^ in[1] ^ in[0];
	assign out[3] = in[3] ^ in[2] ^ in[1] ^ in[0];
endmodule

module binarytobcd(in, out);
	input wire[4: 0] in;
	output wire[5: 0] out;
	reg[3: 0] lsb;
	reg[1: 0] msb;
	integer i;
	always @(in) begin
		msb = 2'b00;
		lsb = 4'b0000;
		for(i = 4; i >= 0; i = i - 1) begin
			if(lsb >= 5) begin
				lsb = lsb + 3;
			end
			msb = msb << 1;
			msb[0] = lsb[3];
			lsb = lsb << 1;
			lsb[0] = in[i];
		end
	end
	assign out = {msb, lsb};
endmodule

module divisiblebythree(in, out);
	input wire[3: 0] in;
	output wire out;
	assign out = (in == 4'b0000) || (in == 4'b0011) || (in == 4'b0110) || (in == 4'b1001) || (in == 4'b1100) || (in == 4'b1111);
endmodule

module updowncounterbehavioural(in, clk, rst, out);
	input wire in, clk, rst;
	output wire[3: 0] out;
	reg[3: 0] _out;
	initial
		_out = 4'b0000;
	always @(posedge clk)
		if(rst == 1'b1)
			_out = 4'b0000;
		else if(in == 1'b1)
			_out = _out + 1;
		else
			_out = _out - 1;
	assign out = _out;
endmodule

module multiplier(in1, in2, out);
	input wire[3: 0] in1;
	input wire[3: 0] in2;
	output wire[7: 0] out;
	wire con[23: 0];
	fulladder f11(in1[0] & in2[0], 1'b0, 1'b0, out[0], con[0]);
	fulladder f12(in1[0] & in2[1], 1'b0, con[0], con[3], con[1]);
	fulladder f13(in1[0] & in2[2], 1'b0, con[1], con[4], con[2]);
	fulladder f14(in1[0] & in2[3], 1'b0, con[2], con[5], con[6]);
	fulladder f21(in1[1] & in2[0], con[3], 1'b0, out[1], con[7]);
	fulladder f22(in1[1] & in2[1], con[4], con[7], con[10], con[8]);
	fulladder f23(in1[1] & in2[2], con[5], con[8], con[11], con[9]);
	fulladder f24(in1[1] & in2[3], con[6], con[9], con[12], con[13]);
	fulladder f31(in1[2] & in2[0], con[10], 1'b0, out[2], con[14]);
	fulladder f32(in1[2] & in2[1], con[11], con[14], con[17], con[15]);
	fulladder f33(in1[2] & in2[2], con[12], con[15], con[18], con[16]);
	fulladder f34(in1[2] & in2[3], con[13], con[16], con[19], con[20]);
	fulladder f41(in1[3] & in2[0], con[17], 1'b0, out[3], con[21]);
	fulladder f42(in1[3] & in2[1], con[18], con[21], out[4], con[22]);
	fulladder f43(in1[3] & in2[2], con[19], con[22], out[5], con[23]);
	fulladder f44(in1[3] & in2[3], con[20], con[23], out[6], out[7]);
endmodule
